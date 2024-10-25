package com.example.prm_group_project

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBContextInstrumentedTest {
    @Test
    fun testDatabaseConnection() {
        val connection = DBContext.connect()


        // Assert that the connection is not null
        Assert.assertNotNull("Connection to database should not be null", connection)

        // Close connection if successful
        if (connection != null) {
            try {
                connection.close()
                Log.i("DBContextInstrumentedTest", "Connection closed.")
            } catch (e: Exception) {
                Log.e("DBContextInstrumentedTest", "Error closing connection: " + e.message)
            }
        } else {
            Log.e("DBContextInstrumentedTest", "Database connection failed.")
        }
    }
}