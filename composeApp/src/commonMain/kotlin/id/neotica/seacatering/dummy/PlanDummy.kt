package id.neotica.seacatering.dummy

import id.neotica.seacatering.dummy.model.PlanPackage

val packageList = listOf(
    PlanPackage(
        id = 1,
        name = "Daily Bento",
        description = "Affordable daily lunch for office workers.",
        price = 30000.0,
        imageUrl = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
        duration = "Per Day",
        features = "1 main dish, 1 side dish, rice, mineral water"
    ),
    PlanPackage(
        id = 2,
        name = "Weekly Healthy Meal",
        description = "Nutritious meals for your fitness goals.",
        price = 150000.0,
        imageUrl = "https://www.themealdb.com/images/media/meals/1529444830.jpg",
        duration = "7 Days",
        features = "Low-carb menu, Fresh salad, Detox drink, High protein"
    ),
    PlanPackage(
        id = 3,
        name = "Family Pack",
        description = "Catered meals for a small family.",
        price = 500000.0,
        imageUrl = "https://www.themealdb.com/images/media/meals/wtsvxx1511296896.jpg",
        duration = "7 Days",
        features = "3 portions per meal, Indonesian cuisine, Free delivery"
    ),
    PlanPackage(
        id = 4,
        name = "Event Catering",
        description = "Perfect for meetings, birthdays, or small events.",
        price = 1500000.0,
        imageUrl = "https://www.themealdb.com/images/media/meals/1549542994.jpg",
        duration = "Per Event",
        features = "Up to 30 pax, Buffet setup, Wait staff included"
    ),
    PlanPackage(
        id = 5,
        name = "Monthly Premium",
        description = "No cooking needed for the entire month.",
        price = 1800000.0,
        imageUrl = "https://www.themealdb.com/images/media/meals/xxyupu1468262513.jpg",
        duration = "30 Days",
        features = "2 meals/day, Chef's choice menu, Free snacks, Free delivery"
    )
)