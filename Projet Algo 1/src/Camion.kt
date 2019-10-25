/**
 * @author  Akiyoma
 * @version 25/10/2019
 */

class Camion
{
    private val cities = listOf("Bordeaux", "Paris", "Mios", "Lyon", "Toulouse", "Marseille")
    private val speedMax = 90

    fun getCities(): String
    {
        var citiesTab: String = ""
        for (i in 1..cities.size)
        {
            if (i == cities.size)
            {
                citiesTab += "${cities[i-1]}"
            }
            else
            {
                citiesTab += "${cities[i - 1]}, "
            }
        }
        return citiesTab
    }

    fun acceleration(): Float
    {
        var speed = 0F
        var distStart = 0F
        while (speed < speedMax)
        {
            speed += 10
            distStart += speed * (1F / 60F)
        }
        return distStart
    }

    fun deceleration(): Float
    {
        var speed = speedMax.toFloat()
        var distEnd = 0F
        while (speed > 0)
        {
            if (speed == speedMax.toFloat()) {
                distEnd += speed * (1F / 60F)
            }
            speed -= 10
            distEnd += speed * (1F / 60F)
        }
        return distEnd
    }

    fun startCity()
    {

        print("Entrer une ville de départ : ")
        var startCity = readLine()?.capitalize()

        if (startCity != null && startCity in cities)
        {
            endCity(startCity)
        }
        else
        {
            println("La ville que vous avez rentré est invalide.")
            startCity()
        }
    }

    fun endCity(startCity: String)
    {
        print("Entrer une ville d'arrivé: ")
        val endCity = readLine()?.capitalize()

        if (startCity != endCity)
        {
            if (endCity != null && endCity in cities)
            {
                distance(startCity, endCity)
            }
            else
            {
                println("La ville que vous avez rentré est invalide.")
                endCity(startCity)
            }
        }
        else
        {
            println("Vous avez sélectionné la même ville, veuillez changer.")
            endCity(startCity)
        }
    }

    fun distance(startCity: String, endCity: String)
    {
        var dist: Int = 0
        val bordeaux_paris = 582
        val bordeaux_mios = 43
        val bordeaux_lyon = 550
        val bordeaux_toulouse = 245
        val bordeaux_marseille = 646
        val paris_mios = 626
        val paris_lyon = 463
        val paris_toulouse = 676
        val paris_marseille = 773
        val mios_lyon = 593
        val mios_toulouse = 274
        val mios_marseille = 674
        val lyon_toulouse = 537
        val lyon_marseille = 313
        val toulouse_marseille = 403

        when (startCity)
        {
            "Bordeaux" -> when (endCity)
            {
                "Paris" -> dist = bordeaux_paris
                "Mios" -> dist = bordeaux_mios
                "Lyon" -> dist = bordeaux_lyon
                "Toulouse" -> dist = bordeaux_toulouse
                "Marseille" -> dist = bordeaux_marseille
            }
            "Paris" -> when (endCity)
            {
                "Bordeaux" -> dist = bordeaux_paris
                "Mios" -> dist = paris_mios
                "Lyon" -> dist = paris_lyon
                "Toulouse" -> dist = paris_toulouse
                "Marseille" -> dist = paris_marseille
            }
            "Mios" -> when (endCity)
            {
                "Bordeaux" -> dist = bordeaux_mios
                "Paris" -> dist = paris_mios
                "Lyon" -> dist = mios_lyon
                "Toulouse" -> dist = mios_toulouse
                "Marseille" -> dist = mios_marseille
            }
            "Lyon" -> when (endCity)
            {
                "Bordeaux" -> dist = bordeaux_lyon
                "Paris" -> dist = paris_lyon
                "Mios" -> dist = mios_lyon
                "Toulouse" -> dist = lyon_toulouse
                "Marseille" -> dist = lyon_marseille
            }
            "Toulouse" -> when (endCity)
            {
                "Bordeaux" -> dist = bordeaux_toulouse
                "Paris" -> dist = paris_toulouse
                "Mios" -> dist = mios_toulouse
                "Lyon" -> dist = lyon_toulouse
                "Marseille" -> dist = toulouse_marseille
            }
            "Marseille" -> when (endCity)
            {
                "Bordeaux" -> dist = bordeaux_marseille
                "Paris" -> dist = paris_marseille
                "Mios" -> dist = mios_marseille
                "Lyon" -> dist = lyon_marseille
                "Toulouse" -> dist = toulouse_marseille
            }
        }

        fun time(dist: Int): String
        {
            val distStart = acceleration()
            val distEnd = deceleration()
            val dist1h42 =  speedMax * 1.7F
            val dist2h = distStart + dist1h42 + distEnd
            val nPause: Int = (dist / dist2h).toInt()
            val pauseTime = 0.25F
            var time: Float = nPause * 2F + nPause * pauseTime
            var remainingDist: Float = dist - (nPause * dist2h)
            if (remainingDist >= distStart + distEnd)
            {
                remainingDist -= distStart + distEnd
                time += 0.3F
                time += remainingDist / speedMax
            }
            else
            {
                time += 0.15F
            }
            return convertHour(time)
        }

        val trajet = arrayOf("$startCity", "$endCity", "$dist KM", "${time(dist)}")
        println(" ______________________________________________________ ")
        println("| Ville de départ | Ville d'arrivée | Distance | Temps |")
        println("|------------------------------------------------------|")
        println(java.lang.String.format("| %-15s | %-15s | %-8s | %-5s |", trajet[0], trajet[1], trajet[2], trajet[3]))
        println(" ------------------------------------------------------ ")
    }



    fun convertHour(time: Float): String
    {
        var hour = time.toInt()
        var minutes = kotlin.math.ceil((time - hour) * 60).toInt()
        return java.lang.String.format("%d:%02d", hour, minutes)
    }
}