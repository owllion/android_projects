package com.example.jettodo.di

import android.content.Context
import androidx.room.Room
import com.example.jettodo.dataSource.TodoDatabase
import com.example.jettodo.dataSource.TodoDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//------------------------------------------
//一開始還沒build的時候，可以發現下面兩個fn都是unused狀態
// 我們也不需要去用他，因為那是hilt的工作
//他會用這些fn去創建injection需要的instance(也就是dependencies)
//---------------------------------
@InstallIn(SingletonComponent::class) //->這個InstallIn可以讓我們refer to Hilt component
//然後Hilt component 的職責就是inject dependencies到corresponding的Android class(但這邊也沒class..)
//總之這是官方解釋啦! 老師解釋是比較簡略的說:我們用這個singleton是因為
//我們希望這個object是single source of truth,且we do not want this to be recreated in
//2 different instance.(就是單例模式啦))

@Module // informs Hilt how to provide instances of certain types.
object AppModule {
    //Dependencies that you provide in Hilt modules are available in all generated components
    // that are associated with the Android class where you install the Hilt module.
    @Singleton //make sure whatever we create here is gonna be a singleton,
    //moaning we just get one instance
    @Provides
    //->第三方class不能用constructor-injection，就要用這個Decorator
    fun provideTodosDao(todoDatabase: TodoDatabase):TodoDatabaseDao
    = todoDatabase.todoDao()
    /**
     * 1.The function return type tells Hilt
     * what type the function provides instances of.
     * 2.The function parameters(todoDatabase) tell Hilt
     * the dependencies of the corresponding type.(TodoDatabaseDao)
     * 3.The function body tells Hilt how to provide an instance of the corresponding type.
     * Hilt executes the function body every time it needs to provide an instance of that type.
     * */

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context): TodoDatabase
        = Room.databaseBuilder(context,TodoDatabase::class.java,"todos_db")
        .fallbackToDestructiveMigration()
        .build()
    //databaseBuilder(hover解釋): A RoomDatabaseBuilder<T> which you can use to create the database.
    //-------------
    //第2個klass參數他指的是:The abstract class which is annotated with Database and extends RoomDatabase.
    //100%和我們創建abstract class TodoDatabase:RoomDatabase()符合!!
    //--------------
    //@ApplicationContext-> 跟localContext差不多概念，就是可以拿到global資訊
    //(It knows everything about it and has access to everything.)
    //---------------
    //::class.java 完全無法理解???!??!?!?
}
