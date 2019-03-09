package com.example.ahmedmakaty.injection.scopes

import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class PerActivity {
}