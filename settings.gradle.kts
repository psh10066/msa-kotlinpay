plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "kotlinpay"
include("membership-service")
include("common")
include("banking-service")
include("money-service")
