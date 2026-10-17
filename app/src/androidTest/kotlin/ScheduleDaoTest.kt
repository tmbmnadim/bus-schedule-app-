import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.ScheduleDao
import com.example.busschedule.data.ScheduleDatabase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ScheduleDaoTest {
    lateinit var inventoryDatabase: ScheduleDatabase
    lateinit var scheduleDao: ScheduleDao

    private var schedule1 = BusSchedule(
        id = 1,
        stopName = "Test1",
        arrivalTimeInMillis = 0
    )

    private var schedule2 = BusSchedule(
        id = 2,
        stopName = "Test2",
        arrivalTimeInMillis = 1000
    )

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsScheduleIntoDB() = runBlocking {
        insertSchedule()
        val allSchedule = scheduleDao.getAll().first()
        assertEquals(allSchedule[0], schedule1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAll_returnsAllSchedule() = runBlocking {
        insertTwoSchedule()
        val allSchedule = scheduleDao.getAll().first()
        assertEquals(allSchedule[0], schedule1)
        assertEquals(allSchedule[1], schedule2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGet_returnCorrectSchedule() = runBlocking {
        insertSchedule()
        val firstSchedule = scheduleDao.getSchedule(schedule1.id).first()
        assertEquals(firstSchedule, schedule1)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesSchedule() = runBlocking {
        insertTwoSchedule()
        scheduleDao.update(schedule1.copy(stopName = "Updated"))
        val allSchedule = scheduleDao.getAll().first()
        assertEquals(allSchedule[0], schedule1.copy(stopName = "Updated"))
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesScheduleFromDB() = runBlocking {
        insertSchedule()
        scheduleDao.delete(schedule1)
        val allSchedule = scheduleDao.getAll().first()
        assertTrue(allSchedule.isEmpty())
    }

    @Before
    fun createDb(){
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context = context, ScheduleDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        scheduleDao = inventoryDatabase.scheduleDao()
    }

    private suspend fun insertSchedule() {
        scheduleDao.insert(schedule1)
    }

    private suspend fun insertTwoSchedule() {
        scheduleDao.insert(schedule1)
        scheduleDao.insert(schedule2)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }
}