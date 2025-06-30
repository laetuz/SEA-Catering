package id.neotica.seacatering

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform