package com.example.i_am_open

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import java.lang.Exception
import kotlin.collections.ArrayList



class DatabaseHelper( val context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "iamopen.db"

    }

    fun companyProduct(companyId: Int) : ArrayList<ProductModel> {
        val sqliteHelper = this.writableDatabase
        val cursor = sqliteHelper.rawQuery("SELECT * from products where companyId=$companyId", null)

        val products = ArrayList<ProductModel>()
        while (cursor.moveToNext()){
            val model = ProductModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6)
            )

            products.add(model)
        }

        cursor.close()
        return products

    }

    fun singleCompany(companyId : Int) : CompanyModel {
        val sqliteDatbase = this.writableDatabase
        val cursor = sqliteDatbase.rawQuery("SELECT * FROM companies where ID= $companyId", null)
        cursor.moveToFirst()

        val model = CompanyModel(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        cursor.close()
        return model



    }

    override fun onCreate(db: SQLiteDatabase?) {
        arrangeDatabase(db)
    }

    private fun arrangeDatabase(db: SQLiteDatabase?) {
        dropTables(db)
        createTables(db)
        insertCompanies(db)
        insertProducts(db)
        insertTutorial(db)
    }

    private fun createTables(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE companies(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT, description TEXT);")
        db?.execSQL("CREATE TABLE products(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT, description TEXT,upVote INTEGER, downVote Integer, companyId INTEGER, FOREIGN KEY(companyId) REFERENCES companies(id));")
        db?.execSQL("CREATE TABLE tutorials(ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, image TEXT, description TEXT, isVideo INTEGER, productId INTEGER, FOREIGN KEY(productId) REFERENCES products(id));")
        db?.execSQL("CREATE TABLE product_precautions(ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, productId INTEGER, FOREIGN KEY(productId) REFERENCES products(id));")
        db?.execSQL("CREATE TABLE liked_products(ID INTEGER PRIMARY KEY AUTOINCREMENT, productId Integer, FOREIGN KEY(productId) REFERENCES products(id));")
    }

    private fun dropTables(db: SQLiteDatabase?) {
        db?.execSQL("Drop Table if exists companies")
        db?.execSQL("Drop Table if exists products")
        db?.execSQL("Drop Table if exists tutorials")
        db?.execSQL("Drop Table if exists product_precautions")
        db?.execSQL("Drop Table if exists liked_products")
    }

    private fun insertCompanies(db: SQLiteDatabase?) {

        db?.execSQL( """Insert into companies values 
            (1, 'MindTree', 'https://images.pexels.com/photos/443383/pexels-photo-443383.jpeg', 'MindTree is well established company developed in 2003 after the boom of internet After Our Innovation we are continuously modify our technology to provide better product for the world.'),
            (2, 'InoCoal', 'https://cdn.pixabay.com/photo/2014/01/30/18/26/skyline-255116_1280.jpg', 'InoCoal is well established company developed in 2006 after the boom of internet\n.After Our Innovation we are continuously modify our technology to provide better product for the world.'),
        (3, 'JrekVi', 'https://cdn.pixabay.com/photo/2019/12/02/08/04/city-4667143_1280.jpg', 'Jrekvi is well established company developed in 2005 after the boom of internet\n.After Our Innovation we are continuously modify our technology to provide better product for the world.'),
        (4, '7^2Half', 'https://cdn.pixabay.com/photo/2019/04/20/11/39/japan-4141578_1280.jpg', '7^2Half(Seven Square 2 half) is a company which provide technological solution better then any other opponent,Clear from its name problem is divided in half portion to find a great solution'),
        (5, 'Rockery', 'https://cdn.pixabay.com/photo/2016/11/29/06/22/buildings-1867772_1280.jpg', 'Rockery started in 2015 most recent company which is dominating in the field of semiconductors.It is providing equal competition to the originally developed companies'),
        (6, 'Zoriyan', 'https://cdn.pixabay.com/photo/2020/02/27/14/33/building-4884852_1280.jpg', 'Zoriyan is new child in the era, Launched in 2021 its main focus is to provide solutions for the problem which are mostly underestimated by common people.')""")
    }

    private  fun insertProducts(db : SQLiteDatabase?) {

        db?.execSQL("""insert into products values 
          (1, 
            "Amazon Dash Button", 
            "https://i.picsum.photos/id/26/4209/2769.jpg?hmac=vcInmowFvPCyKGtV7Vfh7zWcA_Z0kStrPDW3ppP0iGI", 
            "Amazon Dash Button is basically a device that gets connected over internet Wi-Fi and makes sure that the user does not lack important household items like soft drinks, grocery material, medical and personal care, kids, and any pet items ever again.\n It allows the user to order products quickly and there is no need to recall the message again and it also helps to reduce the time frame for searching the required product by the user. This IoT product is developed for making the user's lifestyle simple and easy.", 
            11,1,1
            ), 
            (2, 
                "August Smart Lock", 
                "https://i.picsum.photos/id/619/2509/1673.jpg?hmac=Ya1R0tbWFKtwAl9bKFL7IIlx8YLn_JGYFTiXdINKRPY", 
                "August Smart Lock allows the user to manage their doors from any location hassle-free. It helps the user to keep thieves away and family in your home. It allows the user to know about each person coming and going into your home, provides unlimited digital keys and no fear of stolen key, gives the status updates of your door as it is properly closed or not, provides a good auto-unlock feature and as soon as the user arrives near the door it opens automatically.", 
                12,1,1
                ),
            (3, 
                "Canary", 
                "https://i.picsum.photos/id/819/3264/3264.jpg?hmac=1ZalXesmDNvJwkdpJ1VWdeyBrZT40CSEk3cZ_LDyQsI", 
                "This all-in-one home security system captures video and audio and sends alerts to your smartphone. It automatically knows when you are home or away (no need to enter a security code), and you can also view the live video feed from your phone. Canary's smart alerts distinguish between people and motion and include an image thumbnail, so you know in an instant whether a family member, foe, or a four-legged friend is in your home.", 
                13,4,2
            ),
            (4, 
                "Chamberlain MyQ", 
                "https://i.picsum.photos/id/859/1919/1919.jpg?hmac=24AoHo7Jc5TRRRaJfWO0B4z2wW5Jl14r56rVKeMfpZI", 
                "Chamberlain MyQ products allow you to control your existing garage door with your iPhone or Android device. Make your existing garage door opener smart in minutes with smart garage control. The smart garage control is simple to install and allows you to link your existing garage door opener to the myq app so you can control, secure, and monitor the garage from your smartphone. Once paired with the myq app, it offers more than the ability to simply open and close a garage door. Homeowners with smart garage control benefit from on-the-go management of daily activities, like never having to wonder if the garage door was left open, receiving real-time alerts when children arrive home from school, remotely letting in the dog walker or service person and now in-garage delivery to keep packages safe and secure.", 
                22,11,2
            ),
            (5, 
                "Ring Doorbell", 
                "https://i.picsum.photos/id/946/5000/3572.jpg?hmac=o__WbAcVSi9EvwRNEm2Z6vdOF_S5pMuW6L4zuXhsSvM", 
                "The Ring Doorbell doubles as a door answering device and additional form of home security. With its built-in video camera, Ring senses and records motion near your door, allowing you to communicate with your visitor in real time or review the most important security footage later. It allows to receive real-time notifications on your phone and tablet, get real-time video and audio with the Live View button, control and customize important security settings, save, and share videos, photos with an optional Ring Protect Plan. Control everything with one simple app.", 
                12,10,3
            ), 
            (6, 
                "Clog Doorbell", 
                "https://i.picsum.photos/id/36/4179/2790.jpg?hmac=OCuYYm0PkDCMwxWhrtoSefG5UDir4O0XCcR2x-aSPjs", 
                "The Clog Doorbell doubles as a door answering device and additional form of home security. With its built-in video camera, Ring senses and records motion near your door, allowing you to communicate with your visitor in real time or review the most important security footage later. It allows to receive real-time notifications on your phone and tablet, get real-time video and audio with the Live View button, control and customize important security settings, save, and share videos, photos with an optional Ring Protect Plan. Control everything with one simple app.", 
                32,1,3
            ), 
            (7, 
                "Freshteam", 
                "https://i.picsum.photos/id/48/5000/3333.jpg?hmac=y3_1VDNbhii0vM_FN6wxMlvK27vFefflbUSH06z98so", 
                "Freshteam is the smart HR software for growing businesses. Freshteam helps attract and source top talent through various channels - a quickly customizable career site, integration with multiple free and premium job boards, and social media channels. Once the candidates are in, the recruiters can collaborate with the hiring managers to screen and interview them, share feedback, leave notes for each other, and finally, hire and roll out offers to the best candidates.  Freshteam also enables the HR team to onboard new hires even before day one - whether it’s getting forms filled, documents signed, or handing out handbooks, Freshteam can do it in a few clicks. The HR software also takes complete care of employee time off, employee and manager self-service for employees to raise requests, manager approval workflows, time off reports for teams and the whole organization that give a quick view into upcoming leaves, absenteeism trends, and more.", 
                112,41,4
            ),
            (8, 
                "Hiver", 
                "https://i.picsum.photos/id/60/1920/1200.jpg?hmac=fAMNjl4E_sG_WNUjdU39Kald5QAHQMh-_-TsIbbeDNI", 
                "Hiver is a multi-channel helpdesk built for Google Workspace. It helps teams deliver fast and empathetic customer service, right from the tool they are already familiar with - Gmail. This means they can assign, track, and collaborate on customer emails, as well as run the most advanced analytics and automations from the Gmail interface, without sacrificing any time on learning new software or switching tabs. Teams can also leverage Hiver's Live Chat to engage with their website visitors, and provide real-time support - again, from within Gmail. Hiver helps over 1500 companies - ranging from new-age unicorns to traditional enterprises - deliver a better experience to their customers. Companies like Flexport, Pluralsight, Harvard University, Appsflyer, Oxford Business Group, and Upwork, among others, are powered by Hiver.", 
                22,10,4
            ), 
            (9, 
                "Tidio", 
                "https://i.picsum.photos/id/119/3264/2176.jpg?hmac=PYRYBOGQhlUm6wS94EkpN8dTIC7-2GniC3pqOt6CpNU", 
                "Tidio is a powerful, all-in-one customer service platform that levels up a customer support and helps to generate more sales. An easily accessible live chat widget makes your business available 24/7, while AI-powered chatbots engage your customers in real-time, so you can sell more. Currently used on 300,000+ websites worldwide.", 
                22,12,5
            ),
            (10, 
                "Guru", 
                "https://i.picsum.photos/id/180/2400/1600.jpg?hmac=Ig-CXcpNdmh51k3kXpNqNqcDYTwXCIaonYiBOnLXBb8", 
                "Guru is a company wiki that works in the customer’s workflow, so the information they need to do their job is always at their fingertips. With Guru, the team can create share, access, and update information right in the context of their existing workflow. Guru brings contextual, expert-verified information to the places customers are already working, like Slack, Teams, Email, CRM, their Chrome browser and more.", 
                100,10,5
            ), 
            (11, 
                "StackAdapt", 
                "https://i.picsum.photos/id/201/5000/3333.jpg?hmac=NE8fOMa8u9PBfkq4AVwEoJdRqoPTNwUsyKvKWuXyNCk", 
                "StackAdapt is a self-serve advertising platform that specializes in multi-channel solutions including native, display, video, connected TV, and audio ads. StackAdapt's state-of-the-art programmatic platform is where some of the most progressive work in machine learning meets cutting-edge user experience. StackAdapt is designed around the three core pillars of programmatic planning, executing, and analyzing. StackAdapt is ranked as the number one DSP on G2. For five consecutive years, StackAdapt has been recognized as a high performer and the highest-ranking DSP in customer satisfaction by G2, and placed on the Top 100 Software Products list, and the Highest Satisfaction list for 2020, 2021 and 2022. StackAdapt has been named one of the 2022 Ad Age Best Places to Work. Learn more about StackAdapt here: www.stackadapt.com. ", 
                2,0,6
            ), 
            (12, 
                "Miro", 
                "https://i.picsum.photos/id/225/1500/979.jpg?hmac=jvGoek9ng_Y0GaBbzxN0KJhHaiPtk1VfRcukK8R8FxQ", 
                "Miro is an online, visual collaboration platform designed to unlock creativity and accelerate innovation among teams of all kinds. The platform’s infinite canvas enables teams to lead engaging workshops and meetings, design products, brainstorm ideas, and more. Miro Developer Platform delivers on the idea that one single shared workspace can support all models of work by connecting the most essential tools and apps used by organizations every day. Miro is truly global, with eleven hubs in cities around the world, including San Francisco, Los Angeles, Austin, New York City, Amsterdam, Perm, Berlin, Munich, London, Tokyo, and Sydney", 
                10,10,6
            ), 
            (13, 
                "Paylocity", 
                "https://i.picsum.photos/id/250/4928/3264.jpg?hmac=4oIwzXlpK4KU3wySTnATICCa4H6xwbSGifrxv7GafWU", 
                "Paylocity is an all-in-one software platform gives HR pros a way to easily manage daily tasks in payroll, benefits, talent, and workforce management. But what makes us different is that our technology is backed by a culture that truly cares about our clients’ success. A partner takes the time to get to know you and understand your needs. We work with you to identify the best solutions that will benefit your business today, while paving the way to a better tomorrow.", 
                15,4,1
            ), 
            (14, 
                "Sendoso", 
                "https://i.picsum.photos/id/252/5000/3281.jpg?hmac=43dQtOHPvmpknnjz0R6jenAi-V9OW6J8sVKUTVOpAaU", 
                "Sendoso, the leading Sending Platform, is an effective way to connect with customers and drive revenue with personalized gifts, branded swag, eGifts, virtual experiences, and more.", 
                33,21,1
            ), 
            (15, 
                "NICE CXone", 
                "https://i.picsum.photos/id/366/4000/3000.jpg?hmac=zphhHOH9ofToN2jNHd8z-nc98NrBd8y2okWXEXetLDg", 
                "NICE (formerly NICE inContact) is the cloud contact center. NICE CXone™ combines best-in-class Omnichannel Routing, Analytics, Workforce Optimization, Automation and Artificial Intelligence on an Open Cloud Foundation. NICE’s solution empowers organizations to provide exceptional customer experiences by acting smarter and responding faster to consumer expectations. NICE’s DEVone developer program is an extensive partner ecosystem, providing applications from partner companies on the CXexchange marketplace that are designed to integrate with NICE CXone.", 
                92,11,1
            )
     """)
    }

    fun allCompanies(): ArrayList<CompanyModel> {
        val arrayCompanies = ArrayList<CompanyModel>()
        val sqLiteDatabase = this.readableDatabase


        val cursor = try {
            sqLiteDatabase.rawQuery("Select * from companies", null)
        }catch (e:Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery("Select * from companies", null)
        }

        while (cursor.moveToNext()) {
            val model = CompanyModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )

            arrayCompanies.add(model)
        }
        cursor.close()

        return arrayCompanies
    }

    fun allProducts(companyId: Int = 0) : ArrayList<ProductModel> {
        val arrayProducts = ArrayList<ProductModel>()
        val sqLiteDatabase = this.readableDatabase

        var queryString = "SELECT * FROM products"
        if (companyId != 0) {
            queryString += " WHERE companyId=$companyId"
        }

        val cursor = try {
            sqLiteDatabase.rawQuery(queryString, null)
        }catch (e:Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery(queryString, null)
        }

        while (cursor.moveToNext()) {
            val model = ProductModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6)
            )

            arrayProducts.add(model)
        }

        cursor.close()
        return arrayProducts
    }

    fun singleProduct(productId: Int) : ProductModel {

        val sqLiteDatabase = this.readableDatabase

        val query = "SELECT * FROM products where ID=$productId"

        val cursor = try {
            sqLiteDatabase.rawQuery(query, null)
        }catch (e:Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery(query, null)
        }
        cursor.moveToFirst()
        val product = ProductModel(cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4),
            cursor.getInt(5),
            cursor.getInt(6))
        cursor.close()

        return product
    }

    fun productTutorial(productId: Int = 0, type : TutorialType) : ArrayList<TutorialModel> {
        var query = "SELECT * FROM tutorials"

        if (productId != 0 ){
            query += " where productId = $productId"
            query += when(type){
                TutorialType.VIDEO -> " and isVideo=true"
                TutorialType.READABLE -> " and isVideo=false"
            }
        }

        val tutorials = ArrayList<TutorialModel>()
        val sqLiteDatabase = this.readableDatabase

        val cursor = try {
            sqLiteDatabase.rawQuery(query, null)
        }catch (e:Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery(query, null)
        }

        while (cursor.moveToNext()) {

            val model = TutorialModel(
                id = cursor.getInt(0),
                title = cursor.getString(1),
                image = cursor.getString(2),
                description = cursor.getString(3),
                isVideo = cursor.getInt(4) != 1,
                productId = cursor.getInt(5)
            )
            tutorials.add(model)

        }

        cursor.close()
        return tutorials
    }

    fun singleTutorial(tutorialId : Int) : ProductModel {
        val sqLiteDatabase = this.readableDatabase

        val query = "SELECT * FROM products where ID=$tutorialId"

        val cursor = try {
            sqLiteDatabase.rawQuery(query, null)
        }catch (e:Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery(query, null)
        }
        cursor.moveToFirst()
        val product = ProductModel(cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4),
            cursor.getInt(5),
            cursor.getInt(6))
        cursor.close()

        return product
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun syncProductLike(productId: Int): Boolean {
        val sqliteDatabase = this.writableDatabase
        if (isProductLiked(productId)) {
            return deleteLikeProduct(productId)
        }
        val values = contentValuesOf().apply {
            put("productId", productId)
        }
        val newRowId = sqliteDatabase.insert("liked_products",null, values)
        return newRowId.toInt() != -1
    }

    fun likedProducts() : ArrayList<ProductModel> {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * from products inner join liked_products on products.id=liked_products.productId", null)
        val products = ArrayList<ProductModel>()
        while (cursor.moveToNext()) {
            val model = ProductModel(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6)
            )

            products.add(model)
        }
        cursor.close()
        return products
    }

    fun deleteLikeProduct(productId : Int) : Boolean {
        val sqliteDatabase = this.writableDatabase
        val deletedRaws = sqliteDatabase.delete("liked_products", "productId = ?", arrayOf(productId.toString()))
        return deletedRaws != 0
    }

    fun isProductLiked(productId: Int) : Boolean {
        val sqliteDatabase = this.readableDatabase
        val cursor = sqliteDatabase.rawQuery("Select * from liked_products where productId=$productId", null)
        return cursor.count > 0
    }
    private fun insertTutorial(db: SQLiteDatabase?) {
        db?.execSQL( """Insert into tutorials(title, image, description, isVideo, productId) values 
            ("How do you use a dash button?", "N/A", "Dash Buttons are about the size of a pack of gum. You can stick them around your house using the adhesive on the back or the included clip. Once you set them up, they connect to your home Wi-Fi and order the products you've specified when you press them. Amazon sells dozens of Dash Buttons for various brands.\n
                Full Tutorial:\nAmazon Dash was a consumer goods ordering service which uses proprietary devices and APIs for ordering goods over the Internet.
                Amazon Dash consisted of multiple components, which include:
                the Amazon Dash Wand, a Wi-Fi connected barcode scanner and voice command device, used to reorder consumer goods around the house, integrating with AmazonFresh;
                the Amazon Dash Button, a small consumer electronic device that can be placed around the house and programmed to order a consumer goods such as disinfectant wipes or paper towels; 
                the Amazon Dash Replenishment Service, which allows manufacturers to add a physical button or auto-detection capability to their devices to reorder supplies from Amazon when necessary.
                Amazon Virtual Dash Buttons, which mimic the appearance and function of physical Dash Buttons but are displayed on Amazon's website and some smart devices with displays.\n\nAlternative use\nIn August 2015, within a week of the first shipment of Dash buttons to Amazon Prime members, Popular Mechanics reported that it had already been reprogrammed for use as a push-button data tracker. Computer scientist Edward Benson published instructions online to turn it into a wireless spreadsheet entry device, or a trigger for any other API endpoint. The approach was based on hijacking and re-routing the button's network connection with Amazon's servers.\n
                By May 2016, Consumers' Research pointed out that Amazon Dash was being reprogrammed to use for other purposes such as ordering pizza, tracking time, and controlling lights and outlets in households configured to respond to such commands. In response, Amazon introduced a" 
                , 0, 1)
            """)
    }
}