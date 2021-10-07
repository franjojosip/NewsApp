package hr.fjjukic

import hr.fjjukic.common.di.commonModule
import hr.fjjukic.di.appModule
import ht.ferit.fjjukic.article.di.articleModule
import ht.ferit.fjjukic.menu.di.menuModule
import ht.fjjukic.category.di.categoryModule
import ht.fjjukic.home.di.indexModule
import org.koin.core.module.Module

class App : BaseApp() {

    override fun provideKoinModules(): List<Module> {
        return listOf(
            appModule,
            commonModule,
            indexModule,
            categoryModule,
            articleModule,
            menuModule
        )
    }
}