package com.example.i_am_open

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class DatabaseHelper( val context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "iamopen.db"

    }

    fun companyProduct(companyId: Int) {
        val sqliteHelper = this.writableDatabase
        var execSQL = sqliteHelper.execSQL("SELECT * from products where companyId=$companyId")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE companies(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT, description TEXT);")
        db?.execSQL("CREATE TABLE products(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT, description TEXT,upVote INTEGER, downVote Integer, companyId INTEGER, FOREIGN KEY(companyId) REFERENCES companies(id));")
        db?.execSQL("CREATE TABLE tutorials(ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, image TEXT, description TEXT, isVideo INTEGER, productId INTEGER, FOREIGN KEY(productId) REFERENCES products(id));")
        db?.execSQL("CREATE TABLE product_precautions(ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, productId INTEGER, FOREIGN KEY(productId) REFERENCES products(id));")
//        Timer().schedule(500){
            //calling a function
            insertCompanies(db)
            insertProducts(db)
//        }
    }

    private fun insertCompanies(db: SQLiteDatabase?) {

        db?.execSQL( """Insert into companies values 
            (1, 'MindTree', 'https://unsplash.com/photos/4t6nCthD0es', 'MindTree is well established company developed in 2003 after the boom of internet After Our Innovation we are continuously modify our technology to provide better product for the world.'),
            (2, 'InoCoal', 'https://unsplash.com/photos/PdH0H4S6FTM', 'InoCoal is well established company developed in 2006 after the boom of internet\n.After Our Innovation we are continuously modify our technology to provide better product for the world.'),
        (3, 'JrekVi', 'https://unsplash.com/photos/Ub9LkIWxyec', 'Jrekvi is well established company developed in 2005 after the boom of internet\n.After Our Innovation we are continuously modify our technology to provide better product for the world.'),
        (4, '7^2Half', 'https://unsplash.com/photos/fT6-YkB0nfg', '7^2Half(Seven Square 2 half) is a company which provide technological solution better then any other opponent,Clear from its name problem is divided in half portion to find a great solution'),
        (5, 'Rockery', 'https://unsplash.com/photos/GvPceVqbxm4', 'Rockery started in 2015 most recent company which is dominating in the field of semiconductors.It is providing equal competition to the originally developed companies'),
        (6, 'Zoriyan', 'https://unsplash.com/photos/1deQbU6DhBg', 'Zoriyan is new child in the era, Launched in 2021 its main focus is to provide solutions for the problem which are mostly underestimated by common people.')""")
    }
    private  fun insertProducts(db : SQLiteDatabase?) {
        db?.execSQL("""insert into products values 
  (1, 
    "Amazon Dash Button", 
    "https://unsplash.com/photos/Uwecr7Su3dU", 
    "Amazon Dash Button is basically a device that gets connected over internet Wi-Fi and makes sure that the user does not lack important household items like soft drinks, grocery material, medical and personal care, kids, and any pet items ever again.\n It allows the user to order products quickly and there is no need to recall the message again and it also helps to reduce the time frame for searching the required product by the user. This IoT product is developed for making the user's lifestyle simple and easy.", 
    1
    ), 
    (2, 
        "August Smart Lock", 
        "https://unsplash.com/photos/IJkSskfEqrM", 
        "August Smart Lock allows the user to manage their doors from any location hassle-free. It helps the user to keep thieves away and family in your home. It allows the user to know about each person coming and going into your home, provides unlimited digital keys and no fear of stolen key, gives the status updates of your door as it is properly closed or not, provides a good auto-unlock feature and as soon as the user arrives near the door it opens automatically.", 
        1
        ),
    (3, 
        "Canary", 
        "image", 
        "This all-in-one home security system captures video and audio and sends alerts to your smartphone. It automatically knows when you are home or away (no need to enter a security code), and you can also view the live video feed from your phone. Canary's smart alerts distinguish between people and motion and include an image thumbnail, so you know in an instant whether a family member, foe, or a four-legged friend is in your home.", 
        2
    ),
    (4, 
        "Chamberlain MyQ", 
        "image", 
        "Chamberlain MyQ products allow you to control your existing garage door with your iPhone or Android device. Make your existing garage door opener smart in minutes with smart garage control. The smart garage control is simple to install and allows you to link your existing garage door opener to the myq app so you can control, secure, and monitor the garage from your smartphone. Once paired with the myq app, it offers more than the ability to simply open and close a garage door. Homeowners with smart garage control benefit from on-the-go management of daily activities, like never having to wonder if the garage door was left open, receiving real-time alerts when children arrive home from school, remotely letting in the dog walker or service person and now in-garage delivery to keep packages safe and secure.", 
        2
    ),
    (5, 
        "Ring Doorbell", 
        "image", 
        "The Ring Doorbell doubles as a door answering device and additional form of home security. With its built-in video camera, Ring senses and records motion near your door, allowing you to communicate with your visitor in real time or review the most important security footage later. It allows to receive real-time notifications on your phone and tablet, get real-time video and audio with the Live View button, control and customize important security settings, save, and share videos, photos with an optional Ring Protect Plan. Control everything with one simple app.", 
        3
    ), 
    (6, 
        "Ring Doorbell", 
        "image", 
        "The Ring Doorbell doubles as a door answering device and additional form of home security. With its built-in video camera, Ring senses and records motion near your door, allowing you to communicate with your visitor in real time or review the most important security footage later. It allows to receive real-time notifications on your phone and tablet, get real-time video and audio with the Live View button, control and customize important security settings, save, and share videos, photos with an optional Ring Protect Plan. Control everything with one simple app.", 
        3
    ), 
    (7, 
        "Freshteam", 
        "image", 
        "Freshteam is the smart HR software for growing businesses. Freshteam helps attract and source top talent through various channels - a quickly customizable career site, integration with multiple free and premium job boards, and social media channels. Once the candidates are in, the recruiters can collaborate with the hiring managers to screen and interview them, share feedback, leave notes for each other, and finally, hire and roll out offers to the best candidates.  Freshteam also enables the HR team to onboard new hires even before day one - whether it’s getting forms filled, documents signed, or handing out handbooks, Freshteam can do it in a few clicks. The HR software also takes complete care of employee time off, employee and manager self-service for employees to raise requests, manager approval workflows, time off reports for teams and the whole organization that give a quick view into upcoming leaves, absenteeism trends, and more.", 
        4
    ),
    (8, 
        "Hiver", 
        "image", 
        "Hiver is a multi-channel helpdesk built for Google Workspace. It helps teams deliver fast and empathetic customer service, right from the tool they are already familiar with - Gmail. This means they can assign, track, and collaborate on customer emails, as well as run the most advanced analytics and automations from the Gmail interface, without sacrificing any time on learning new software or switching tabs. Teams can also leverage Hiver's Live Chat to engage with their website visitors, and provide real-time support - again, from within Gmail. Hiver helps over 1500 companies - ranging from new-age unicorns to traditional enterprises - deliver a better experience to their customers. Companies like Flexport, Pluralsight, Harvard University, Appsflyer, Oxford Business Group, and Upwork, among others, are powered by Hiver.", 
        4
    ), 
    (9, 
        "Tidio", 
        "image", 
        "Tidio is a powerful, all-in-one customer service platform that levels up a customer support and helps to generate more sales. An easily accessible live chat widget makes your business available 24/7, while AI-powered chatbots engage your customers in real-time, so you can sell more. Currently used on 300,000+ websites worldwide.", 
        5
    ),
    (10, 
        "Guru", 
        "image", 
        "Guru is a company wiki that works in the customer’s workflow, so the information they need to do their job is always at their fingertips. With Guru, the team can create share, access, and update information right in the context of their existing workflow. Guru brings contextual, expert-verified information to the places customers are already working, like Slack, Teams, Email, CRM, their Chrome browser and more.", 
        5
    ), 
    (11, 
        "StackAdapt", 
        "image", 
        "StackAdapt is a self-serve advertising platform that specializes in multi-channel solutions including native, display, video, connected TV, and audio ads. StackAdapt's state-of-the-art programmatic platform is where some of the most progressive work in machine learning meets cutting-edge user experience. StackAdapt is designed around the three core pillars of programmatic planning, executing, and analyzing. StackAdapt is ranked as the number one DSP on G2. For five consecutive years, StackAdapt has been recognized as a high performer and the highest-ranking DSP in customer satisfaction by G2, and placed on the Top 100 Software Products list, and the Highest Satisfaction list for 2020, 2021 and 2022. StackAdapt has been named one of the 2022 Ad Age Best Places to Work. Learn more about StackAdapt here: www.stackadapt.com. ", 
        6
    ), 
    (12, 
        "Miro", 
        "image", 
        "Miro is an online, visual collaboration platform designed to unlock creativity and accelerate innovation among teams of all kinds. The platform’s infinite canvas enables teams to lead engaging workshops and meetings, design products, brainstorm ideas, and more. Miro Developer Platform delivers on the idea that one single shared workspace can support all models of work by connecting the most essential tools and apps used by organizations every day. Miro is truly global, with eleven hubs in cities around the world, including San Francisco, Los Angeles, Austin, New York City, Amsterdam, Perm, Berlin, Munich, London, Tokyo, and Sydney", 
        6
    ), 
    (13, 
        "Paylocity", 
        "image", 
        "Paylocity is an all-in-one software platform gives HR pros a way to easily manage daily tasks in payroll, benefits, talent, and workforce management. But what makes us different is that our technology is backed by a culture that truly cares about our clients’ success. A partner takes the time to get to know you and understand your needs. We work with you to identify the best solutions that will benefit your business today, while paving the way to a better tomorrow.", 
        1
    ), 
    (14, 
        "Sendoso", 
        "image", 
        "Sendoso, the leading Sending Platform, is an effective way to connect with customers and drive revenue with personalized gifts, branded swag, eGifts, virtual experiences, and more.", 
        1
    ), 
    (15, 
        "NICE CXone", 
        "image", 
        "NICE (formerly NICE inContact) is the cloud contact center. NICE CXone™ combines best-in-class Omnichannel Routing, Analytics, Workforce Optimization, Automation and Artificial Intelligence on an Open Cloud Foundation. NICE’s solution empowers organizations to provide exceptional customer experiences by acting smarter and responding faster to consumer expectations. NICE’s DEVone developer program is an extensive partner ecosystem, providing applications from partner companies on the CXexchange marketplace that are designed to integrate with NICE CXone.", 
        1
    )
     """)
    }

    fun allCompanies(): ArrayList<CompanyModel> {
        val sqLiteDatabase = this.readableDatabase
        var cursor: Cursor = sqLiteDatabase.rawQuery("Select * from companies", null)
        var arrayCompanies : ArrayList<CompanyModel> = ArrayList<CompanyModel>()
        while (cursor.moveToNext()) {
            var model : CompanyModel = CompanyModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )

            arrayCompanies.add(model)
        }
        return arrayCompanies
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }



    fun test() : String{
        val sqLiteDatabase = this.readableDatabase
        return """working"""
//        val cursor = sqLiteDatabase.execSQL("SELECT * FROM companies")
//        return cursor.toString()
    }
}