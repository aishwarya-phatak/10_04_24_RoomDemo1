package com.bitcode.a10_04_24_roomdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
       val productDatabase = Room.databaseBuilder(
           this,
           ProductDatabase::class.java,
           "db_products"
       ).allowMainThreadQueries().build()

       val productDao = productDatabase.getProductDao()
        productDao.insert(
            Product(
            1,
            "Product1",
            1000
        )
        )

        productDao.insert(
            Product(
            2,
            "Product2",
            2000
        )
        )

        productDao.insert(
            Product(
            3,
            "Product3",
            3000
        )
        )

        var products = productDao.getProducts()
        for (eachProduct in products){
            Log.e("tag", "Each product -- ${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }

        productDao.update(Product(
            3,
            "New Title 3",
            4563
        ))

        for (eachProduct in productDao.getProducts()){
            Log.e("tag", "Each Product -- ${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }

        productDao.delete(
            Product(
                2,
                "Product2",
                2000
            )
        )

        for (eachProduct in productDao.getProducts()){
            Log.e("tag", "${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }
    }
}