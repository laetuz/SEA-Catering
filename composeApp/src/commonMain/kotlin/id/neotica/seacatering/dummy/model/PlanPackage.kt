package id.neotica.seacatering.dummy.model

data class PlanPackage(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val duration: String,
    val features: String
)
