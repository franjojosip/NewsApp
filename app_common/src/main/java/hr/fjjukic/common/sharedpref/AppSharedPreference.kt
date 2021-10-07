package hr.fjjukic.common.sharedpref

interface AppSharedPreference {
    var apiURL: String
    var appLocale: String
    var appVersion: String
    var authToken: String?
    var refreshToken: String?
}