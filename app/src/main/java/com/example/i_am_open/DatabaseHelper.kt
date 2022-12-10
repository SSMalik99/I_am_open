package com.example.i_am_open

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.i_am_open.interfaces.LegalPrecautionInterface
import java.lang.Exception
import kotlin.collections.ArrayList


class DatabaseHelper(val context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "iamopen.db"
    }

    // To fetch products based on the company Id
    fun companyProduct(companyId: Int): ArrayList<ProductModel> {
        val sqliteHelper = this.writableDatabase
        val cursor =
            sqliteHelper.rawQuery("SELECT * from products where companyId=$companyId", null)

        val products = ArrayList<ProductModel>()
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

            products.add(model)
        }

        cursor.close()
        return products

    }

    // To fetch the single company data based on the company Id
    fun singleCompany(companyId: Int): CompanyModel {
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

    // Code to drop tables -> create tables -> seed initial data in respective tables
    private fun arrangeDatabase(db: SQLiteDatabase?) {
        dropTables(db)
        createTables(db)
        insertCompanies(db)
        insertProducts(db)
        insertTutorial(db)
        insertLegallyPrecautions(db)
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

    // To seed companies
    private fun insertCompanies(db: SQLiteDatabase?) {

        db?.execSQL(
            """Insert into companies values 
            (1, 'MindTree', 'https://images.pexels.com/photos/443383/pexels-photo-443383.jpeg', 'MindTree is well established company developed in 2003 after the boom of internet After Our Innovation we are continuously modify our technology to provide better product for the world.'),
            (2, 'InoCoal', 'https://cdn.pixabay.com/photo/2014/01/30/18/26/skyline-255116_1280.jpg', 'InoCoal is well established company developed in 2006 after the boom of internet\n.After Our Innovation we are continuously modify our technology to provide better product for the world.'),
        (3, 'JrekVi', 'https://cdn.pixabay.com/photo/2019/12/02/08/04/city-4667143_1280.jpg', 'Jrekvi is well established company developed in 2005 after the boom of internet\n.After Our Innovation we are continuously modify our technology to provide better product for the world.'),
        (4, '7^2Half', 'https://cdn.pixabay.com/photo/2019/04/20/11/39/japan-4141578_1280.jpg', '7^2Half(Seven Square 2 half) is a company which provide technological solution better then any other opponent,Clear from its name problem is divided in half portion to find a great solution'),
        (5, 'Rockery', 'https://cdn.pixabay.com/photo/2016/11/29/06/22/buildings-1867772_1280.jpg', 'Rockery started in 2015 most recent company which is dominating in the field of semiconductors.It is providing equal competition to the originally developed companies'),
        (6, 'Zoriyan', 'https://cdn.pixabay.com/photo/2020/02/27/14/33/building-4884852_1280.jpg', 'Zoriyan is new child in the era, Launched in 2021 its main focus is to provide solutions for the problem which are mostly underestimated by common people.')"""
        )
    }

    // To seed products
    private fun insertProducts(db: SQLiteDatabase?) {

        db?.execSQL(
            """insert into products values 
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
     """
        )
    }

    // To list all of the companies
    fun allCompanies(): ArrayList<CompanyModel> {
        val arrayCompanies = ArrayList<CompanyModel>()
        val sqLiteDatabase = this.readableDatabase


        val cursor = try {
            sqLiteDatabase.rawQuery("Select * from companies", null)
        } catch (e: Exception) {
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

    // To fetch all of the products within the particular company
    fun allProducts(companyId: Int = 0): ArrayList<ProductModel> {
        val arrayProducts = ArrayList<ProductModel>()
        val sqLiteDatabase = this.readableDatabase

        var queryString = "SELECT * FROM products"
        if (companyId != 0) {
            queryString += " WHERE companyId=$companyId"
        }

        val cursor = try {
            sqLiteDatabase.rawQuery(queryString, null)
        } catch (e: Exception) {
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

    // To fetch single product data based on provided product Id
    fun singleProduct(productId: Int): ProductModel {

        val sqLiteDatabase = this.readableDatabase

        val query = "SELECT * FROM products where ID=$productId"

        val cursor = try {
            sqLiteDatabase.rawQuery(query, null)
        } catch (e: Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery(query, null)
        }
        cursor.moveToFirst()
        val product = ProductModel(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4),
            cursor.getInt(5),
            cursor.getInt(6)
        )
        cursor.close()

        return product
    }

    // To fetch tutorial data of the product base on provided product Id and tutorial type
    fun productTutorial(productId: Int = 0, type: TutorialType): ArrayList<TutorialModel> {
        var query = "SELECT * FROM tutorials"

        if (productId != 0) {
            query += " where productId = $productId"
            query += when (type) {
                TutorialType.VIDEO -> " and isVideo=1"
                TutorialType.READABLE -> " and isVideo=0"
            }
        }

        val tutorials = ArrayList<TutorialModel>()
        val sqLiteDatabase = this.readableDatabase

        val cursor = try {
            sqLiteDatabase.rawQuery(query, null)
        } catch (e: Exception) {
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

    // To fetch a product tutorial base on provided tutorial id
    fun singleTutorial(tutorialId: Int): TutorialModel {
        val sqLiteDatabase = this.readableDatabase

        val query = "SELECT * FROM tutorials where ID=$tutorialId"

        val cursor = try {
            sqLiteDatabase.rawQuery(query, null)
        } catch (e: Exception) {
            arrangeDatabase(sqLiteDatabase)
            sqLiteDatabase.rawQuery(query, null)
        }
        cursor.moveToFirst()
        val tutorial = TutorialModel(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4) == 1,
            cursor.getInt(5),
        )
        cursor.close()

        return tutorial
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    // Sync product like means if
    fun syncProductLike(productId: Int): Boolean {
        val sqliteDatabase = this.writableDatabase
        if (isProductLiked(productId)) {
            return deleteLikeProduct(productId)
        }
        val values = contentValuesOf().apply {
            put("productId", productId)
        }
        val newRowId = sqliteDatabase.insert("liked_products", null, values)
        return newRowId.toInt() != -1
    }

    // fetch all the products from the database
    fun likedProducts(): ArrayList<ProductModel> {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * from products inner join liked_products on products.id=liked_products.productId",
            null
        )
        val products = ArrayList<ProductModel>()
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

            products.add(model)
        }
        cursor.close()
        return products
    }

    // delete the product like from the database
    fun deleteLikeProduct(productId: Int): Boolean {
        val sqliteDatabase = this.writableDatabase
        val deletedRaws =
            sqliteDatabase.delete("liked_products", "productId = ?", arrayOf(productId.toString()))
        return deletedRaws != 0
    }

    // check whether product is liked or not
    fun isProductLiked(productId: Int): Boolean {
        val sqliteDatabase = this.readableDatabase
        val cursor =
            sqliteDatabase.rawQuery("Select * from liked_products where productId=$productId", null)
        val isLiked = cursor.count > 0
        cursor.close()
        return isLiked
    }

    // insert Tutorials in the database
    private fun insertTutorial(db: SQLiteDatabase?) {
        db?.execSQL(
            """Insert into tutorials(title, image, description, isVideo, productId) values 
            ("How do you use a dash button?", "N/A", 
"Dash Buttons are about the size of a pack of gum. You can stick them around your house using the adhesive on the back or the included clip. Once you set them up, they connect to your home Wi-Fi and order the products you've specified when you press them. Amazon sells dozens of Dash Buttons for various brands.
Full Tutorial:

Amazon Dash was a consumer goods ordering service which uses proprietary devices and APIs for ordering goods over the Internet.
Amazon Dash consisted of multiple components, which include:
the Amazon Dash Wand, a Wi-Fi connected barcode scanner and voice command device, used to reorder consumer goods around the house, integrating with AmazonFresh;
the Amazon Dash Button, a small consumer electronic device that can be placed around the house and programmed to order a consumer goods such as disinfectant wipes or paper towels; 
the Amazon Dash Replenishment Service, which allows manufacturers to add a physical button or auto-detection capability to their devices to reorder supplies from Amazon when necessary.
Amazon Virtual Dash Buttons, which mimic the appearance and function of physical Dash Buttons but are displayed on Amazon's website and some smart devices with displays.


Alternative use
In August 2015, within a week of the first shipment of Dash buttons to Amazon Prime members, Popular Mechanics reported that it had already been reprogrammed for use as a push-button data tracker. Computer scientist Edward Benson published instructions online to turn it into a wireless spreadsheet entry device, or a trigger for any other API endpoint. The approach was based on hijacking and re-routing the button's network connection with Amazon's servers.

By May 2016, Consumers' Research pointed out that Amazon Dash was being reprogrammed to use for other purposes such as ordering pizza, tracking time, and controlling lights and outlets in households configured to respond to such commands. In response, Amazon introduced a programmer-friendly, but more expensive button in the form of an Internet of Things Dash Button which allows programmers to make programming modifications to the device.", 0, 1),
(
"Short Tutorial", "N/A", 
"The August lock is feature rich and offers plenty of integrations. The features we disliked (auto unlock, laggy Alarm.com integration, and DoorSense) paled in comparison to the overall stellar performance of the lock. And remember, some of our issues were self-created thanks to Door Armor."
,0,2),
("Using the August App", "N/A",
"The real beauty of a smart lock is app control, and the August app is easy to use, responsive, and organized. First things first, there’s Keychain. From here, you can click in to control individual locks if you happen to have multiple locks. Once you click in, you will be presented with four tabs.
From the first tab, you can lock and unlock the door. From the second tab, you can view a thirty-day activity history. You can see when the lock was locked/unlocked both manually and by a user.
The third tab is your guest list. You can add multiple users and divide them into two categories: Owner and Guest. First of all, though Owners have ultimate access, you can have multiple owners. Second, you can also have multiple guests, and each guest can have settings unique to their access.
In digging into the guest access settings, you’ll find “always”, “recurring”, and “temporary” access. Recurring is great for cleaners or dog walkers or people who visit on a regular schedule. You can set their access by day of the week and time. For example, Mondays and Thursdays between 4 PM and 7 PM. The downside here is that recurring means weekly, so this doesn’t work great if you have a monthly or bi-weekly cleaning crew.",
            0,2),
("How does it work?", "N/A",
"Motion detection is the key for Canary. When it senses something moving, it starts recording that video stream and, whether day or night, the clips are crystal clear thanks to infrared LED technology and a solid camera array. Canary sometimes captures false positives but, if you start to tag these events, then your Canary begins to learn what it needs to ignore. What's more, a recent update means that you can dial up or down its sensitivity to help calibrate it even further.
The other time that it needs to ignore movement is, of course, when you and your housemates are home. Fortunately, there's no need to manually arm and disarm Canary each time you come and go. Instead, you download the Canary app to your mobile and it will detect whether that phone is home or not through GPS, cellular data and Wi-Fi, and, yes, you can have more than one phone connected to a single Canary. Of course, if you wish to arm the device when you go up to bed at night, then that's perfectly possible too.
Lastly, one of the most important parts of the system to get your head around is your timeline. It's here, on the app, where Canary sends the clips it captures for you to view. You can filter them by events that occurred only while armed or by ones that you've bookmarked for whatever reason. It's also possible to download video clips depending upon your subscription plan.
",
    0,3),
    (
    "N/A",
    "N/A","https://www.youtube.com/watch?v=pJycjYkQNQo",1,3
    ),(
    "How does it work?", "N/A",
"Motion detection is the key for Canary. When it senses something moving, it starts recording that video stream and, whether day or night, the clips are crystal clear thanks to infrared LED technology and a solid camera array. Canary sometimes captures false positives but, if you start to tag these events, then your Canary begins to learn what it needs to ignore. What's more, a recent update means that you can dial up or down its sensitivity to help calibrate it even further.
The other time that it needs to ignore movement is, of course, when you and your housemates are home. Fortunately, there's no need to manually arm and disarm Canary each time you come and go. Instead, you download the Canary app to your mobile and it will detect whether that phone is home or not through GPS, cellular data and Wi-Fi, and, yes, you can have more than one phone connected to a single Canary. Of course, if you wish to arm the device when you go up to bed at night, then that's perfectly possible too.
Lastly, one of the most important parts of the system to get your head around is your timeline. It's here, on the app, where Canary sends the clips it captures for you to view. You can filter them by events that occurred only while armed or by ones that you've bookmarked for whatever reason. It's also possible to download video clips depending upon your subscription plan.
", 0,4
    ),(
    "N/A", "N/A", "https://www.youtube.com/watch?v=pJycjYkQNQo", 1, 4
    ),(
    "The who, what and how", "N/A",
    "Who this is for: The Chamberlain MyQ is for someone who wants to control or keep tabs on their garage door from near or afar.
What you need to know: Setting up this gadget took about 15 minutes, and it’s quite intuitive. Chamberlain says the MyQ supports most garage door openers made after 1993, but mileage could potentially vary. It also doesn’t support Amazon Alexa or Apple HomeKit integrations.
How it compares: For $29.98 (when it’s not on sale), the Chamberlain MyQ is a no-brainer addition to a home with a garage door. It doesn’t require the long install or cost of a new garage door opener with a smart hub built in and supports a long list of openers. Chances are that includes the one in your home.
Legal Precautions
The idea of controlling your garage door remotely and verifying that everything is secure at home, or having packages delivered directly into your garage is enticing for many people. The convenience that many of these IOT devices provide often persuades consumers away from thinking about the possible security concerns.
McAfee Advanced Threat Research recently investigated Chamberlain’s MyQ Hub, a “Universal” garage door automation platform. The way Chamberlain has made this device universal is via a Hub, which acts as a new garage door opener, similar to the one that you would have in your car. This allows the MyQ Hub to retrofit and work with a wide variety of garage doors.
We found that Chamberlain did a fairly good job of securing this device, which is typically uncommon for IOT devices. However, we discovered that there is a flaw in the way the MyQ Hub communicates with the remote sensor over radio frequencies.
From an attack perspective there are three main vectors that we began to look at: local network, remote access (API, or third-party integration), and RF communications between the sensor and the Hub. The first thing we attempted was to gain access to the device via the local network. A quick port scan of the device revealed that it was listening on port 80. When attempting to navigate to the device at port 80 it would redirect to start.html and return a 404 error. No other ports were open on the device.
",0,5
    ),(
    "N/A", "N/A", "https://youtu.be/U86WHwOXFm4", 1, 5
    ),(
    "How Does a Ring Doorbell Work? What You Need to Know", "N/A",
    "The smart doorbell's camera provides a clear picture of everything and everyone at your door. You can view visitors and check on motion through the app day or night. At night, the quality doesn't suffer because the device uses infrared technology for its night vision.
If you don't have a drill or aren't able or allowed to drill holes on the property, you can opt for the Ring No-Drill Mount. It's a mounting plate designed for the Ring Video Doorbell which lets you mount the device without having to make holes.
You'll need a wireless router for installing the video doorbell as it requires Internet access. To complete the process, download the Ring app, and follow the further instructions. You can get the Ring app for iOS and Android. The app is free and lets you access a video stream of what's outside your door.
Just to note, you don't have to have an existing doorbell to install a Ring one, so that shouldn't deter you from getting one.
", 0, 6
    ), ("N/A", "N/A", "https://www.youtube.com/watch?v=Fw2o31gFqHE", 1, 1),
    ("N/A", "N/A", "https://www.youtube.com/watch?v=aFYs9zqYpdM", 1, 1),
    ("N/A", "N/A", "youtube.com/watch?v=dIXjed-MU5I", 1, 1),
    ("N/A", "N/A", "https://www.youtube.com/watch?v=4_0Jm3WCpOw", 1, 2),
    ("N/A", "N/A", "https://www.youtube.com/watch?v=egVr3cuP9nQ", 1, 2),
    ("N/A", "N/A", "https://www.youtube.com/watch?v=aDt3TZSQtPU", 1, 2)
    """
        )

    }


    // Insert Legal precautions in the database
    private fun insertLegallyPrecautions(db: SQLiteDatabase?) {
        db?.execSQL(
            """Insert into product_precautions (title, description, productId) values 
            ("Legal Precautions",
"Is the device safe to use and to hook up with your local network or are you opening a box of worms by installing this in your home?
Unfortunately the Amazon PR team declined to answer our questions even though it previously seemed like they were going to work with us on this report. However we got great insight from industry and adoption experts, which we want to share with you.
It should be remembered that items such as phones and printers have been used to access networks, I have also seen smart toasters and kettles hacked. The IoT is an area of concern within the security industry, if high value devices with huge development budgets can be compromised despite care and attention to security then it is likely little to no care will be taken with the unsophisticated almost throw away devices. In themselves there is little risk despite ordering an overabundance of detergent, but once these integrate more tightly with other devices the risks rise, and of course they would potentially provide access to the communication networks they utilize. But note that this is all speculation, there is zero evidence that I am aware of that suggest the Dash Button might introduce risk.”


Never underestimate the ingenuity of hackers
There is indeed no evidence on the device bringing harm. But is there perhaps a way of mitigating risk even further? We talked with Sr. Security Researcher Stephen Cobb from ESET and he says, “I don’t have data on reliability, but given the need to make these as minimal as possible I have to wonder how they will perform in damp locations and remote parts of the house. Battery life and corrosion could be an issue too, although I have no data on these aspects. Data leakage during transmission is a possibility but that data has limited potential for abuse. The devices themselves can be hacked for a variety of uses including triggering events on a network.
", 1), (
"What could happen if something goes wrong?",
"Smart locks are one of those connected devices that seem to worry lots of people. The pros: They offer a lot of convenience with multiple ways to unlock the door to your home, a way to track who comes and goes from your home, they can allow you give out a keypad number to people like a babysitter and then revoke that when they no longer need access, and they can help you make sure you locked your front door when your anxiety kicks in on vacation. The cons: They can be vulnerable to any number of things such as power outages, lost or compromised phones, ransomware attacks on the company who made your lock, product security vulnerabilities, WiFi and/or Bluetooth vulnerabilities, home hub vulnerabilities, bad software updates, data leaks, and more.
With all that said, how do August Smart Locks stack up? On the security side, in 2019, security researchers found a vulnerability in a version of August's smart locks that use the Connect add-on to connect to WiFi that could let hackers take over your home WiFi. The versions of August smart locks with WiFi built-in didn't seem to be vulnerable to this security threat.
On the privacy side of things, August seems to do better. August doesn't sell your personal information, which is good. They also say they will only use your personal information to send you relevant content where they have your consent to do so. This is also good. All in all, August seems to do a good job with users’ privacy. Just be careful with what you share with third parties such as Alexa or Google Assistant as they can collect data on you too.
What’s the worst that could happen? Well, it’s entirely possible August could suffer a ransomware attack where bad people hold all smart lock access hostage unless August pays up. You can’t get into your home, your pup really has to pee, and things go south from there. You and your pup really don’t want that.
",2
), (
"Privacy and security auditing for Canary devices.",
"We have strict internal policies and barriers in place to ensure that all personal customer data remains private and secure within the Canary Cloud at all times. Only select Canary employees have access keys to systems that contain sensitive customer information. Authorized access to the Canary Cloud is granted on a least-privilege basis.

Third-party security consultants audit our systems regularly. We constantly monitor the security landscape for new threats and potential risks, and improve our system accordingly using proven technologies. As improvements are made, the Canary Cloud, Canary app, and embedded Canary firmware will be updated regularly to provide users with our most recent security advancements.

Transparency and user accounts.
Our system balances personal privacy with principles of mutual trust and transparency. Individual users have unique passwords and login credentials which are stored using a salted one-way hash function. Users can also easily customize settings to disable the camera and microphone. Additionally, the Canary app promotes information-sharing and visibility between members of the same household; the in-app timeline provides a shared view of what’s actually happening at home, including mode changes, events, and activity.
",3
),(
"Legal Precautions",
"The idea of controlling your garage door remotely and verifying that everything is secure at home, or having packages delivered directly into your garage is enticing for many people. The convenience that many of these IOT devices provide often persuades consumers away from thinking about the possible security concerns.

McAfee Advanced Threat Research recently investigated Chamberlain’s MyQ Hub, a “Universal” garage door automation platform. The way Chamberlain has made this device universal is via a Hub, which acts as a new garage door opener, similar to the one that you would have in your car. This allows the MyQ Hub to retrofit and work with a wide variety of garage doors.

We found that Chamberlain did a fairly good job of securing this device, which is typically uncommon for IOT devices. However, we discovered that there is a flaw in the way the MyQ Hub communicates with the remote sensor over radio frequencies.
From an attack perspective there are three main vectors that we began to look at: local network, remote access (API, or third-party integration), and RF communications between the sensor and the Hub. The first thing we attempted was to gain access to the device via the local network. A quick port scan of the device revealed that it was listening on port 80. When attempting to navigate to the device at port 80 it would redirect to start.html and return a 404 error. No other ports were open on the device.
",4
),(
"Legal Precautions",
"Like any piece of technology, video doorbells are vulnerable to hacking. In 2019, The Washington Post reported that a hacker gained access to a Ring camera in a children's room and began talking to the 8-year-old girl who was in the room.
While Ring has since taken steps to increase security, this incident highlights the importance of being vigilant about the security of all your devices.
Your Ring Doorbell can be hacked and used to spy on you or your family if you don't take the necessary precautions. From regular software updates to strong passwords and two-factor authentication, there are several ways you can help keep your Ring doorbell safe from hackers.
All Wi-Fi-enabled devices are potential targets for hackers, even cloud-based ones like the Ring.
Cloud storage is a great way to ensure that you never lose your footage, but it also means that someone else can access it.
", 5
),(
"Generic Privacy Policy",
"This privacy policy  will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences", 6
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
7
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
8
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
9
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
10
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
11
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
12
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
13
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
14
),(
"Generic Privacy Policy",
"This privacy policy will help you understand how we uses and
protects the data you provide to us when you visit and use service.
We reserve the right to change this policy at any given time, of which you will be promptly
updated. If you want to make sure that you are up to date with the latest changes, we advise
you to frequently visit this page.
What User Data We Collect
When you visit the website, we may collect the following data:
• Your IP address.
• Your contact information and email address.
• Other information such as interests and preferences.
• Data profile regarding your online behavior on our website.
Why We Collect Your Data
We are collecting your data for several reasons:
• To better understand your needs.
• To improve our services and products.
• To send you promotional emails containing the information we think you will find
interesting.
• To contact you to fill out surveys and participate in other types of market research.
• To customize our website according to your online behavior and personal preferences",
15
)
"""
        )
    }

    // get the precautions detail for the product from the database
    fun getProductPrecaution(productId: Int): LegalPrecautionInterface {
        val sqliteDatabase = this.readableDatabase
        val cursor = sqliteDatabase.rawQuery(
            "SELECT * From product_precautions where productId= $productId",
            null
        )
        cursor.moveToFirst()
        val precaution = LegalPrecautionInterface(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getInt(3)
        )
        cursor.close()
        return precaution
    }
}