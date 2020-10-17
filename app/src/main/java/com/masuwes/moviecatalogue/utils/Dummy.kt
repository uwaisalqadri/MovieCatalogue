package com.masuwes.moviecatalogue.utils

import com.masuwes.moviecatalogue.model.MovieModel
import com.masuwes.moviecatalogue.model.TvShowModel

object Dummy {

    private const val imageUrl = "https://image.tmdb.org/t/p/w1280/"

    fun generateDummyMovie(): ArrayList<MovieModel> {

        val movies = ArrayList<MovieModel>()


        movies.add(
            MovieModel(
                "512200",
                "A Street Cat Named Bob",
                "Based on the international best selling book. The true feel good story of how James Bowen, a busker and recovering drug addict, had his life transformed when he met a stray ginger cat.",
                "${imageUrl}6ltgCFYvw0wL7460ldFMMZv4txj.jpg",
                "November 4, 2016",
                "7.9"
            )
        )


        movies.add(
            MovieModel(
                "419704",
                "Your Name.",
                "High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places. Mitsuha wakes up in Taki’s body, and he in hers. This bizarre occurrence continues to happen randomly, and the two must adjust their lives around each other.",
                "${imageUrl}q719jXXEzOoYaps6babgKnONONX.jpg",
                "August 26, 2016",
                "8.2"
            )
        )

        movies.add(
            MovieModel(
                "330457",
                "Frozen II",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "${imageUrl}pjeMs3yqRmFL3giJy4PMXWZTTPa.jpg",
                "Feb 05, 2020",
                "8.6"
            )
        )


        movies.add(
            MovieModel(
                "492188",
                "Grave of the Fireflies",
                "In the final months of World War II, 14-year-old Seita and his sister Setsuko are orphaned when their mother is killed during an air raid in Kobe, Japan. After a falling out with their aunt, they move into an abandoned bomb shelter. With no surviving relatives and their emergency rations depleted, Seita and Setsuko struggle to survive.",
                "${imageUrl}wcNkHDbyc290hcWk7KXbBZUuXpq.jpg",
                "April 16, 1988",
                "8.4"
            )
        )

        movies.add(
            MovieModel(
                "449924",
                "葉問4",
                "Ip Man 4 is an upcoming Hong Kong biographical martial arts film directed by Wilson Yip and produced by Raymond Wong. It is the fourth in the Ip Man film series based on the life of the Wing Chun grandmaster of the same name and features Donnie Yen reprising the role. The film began production in April 2018 and ended in July the same year.",
                "${imageUrl}vn94LlNrbUWIZZyAdmvUepFBeaY.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )

        movies.add(
            MovieModel(
                "181812",
                "Star Wars: The Rise of Skywalker",
                "The next installment in the franchise and the conclusion of the “Star Wars“ sequel trilogy as well as the “Skywalker Saga.",
                "${imageUrl}db32LaOibwEliAmSL2jjDF6oDdj.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )

        movies.add(
            MovieModel(
                "1331",
                "Boruto: Naruto the Movie",
                "Boruto is the son of the 7th Hokage Naruto who completely rejects his father. Behind this, he has feelings of wanting to surpass Naruto, who is respected as a hero. He ends up meeting his father's friend Sasuke, and requests to become... his apprentice!? The curtain on the story of the new generation written by Masashi Kishimoto rises!",
                "${imageUrl}1k6iwC4KaPvTBt1JuaqXy3noZRY.jpg",
                "August 7, 2015",
                "7.8"
            )
        )

        movies.add(
            MovieModel(
                "509967",
                "6 Underground",
                "After faking his death, a tech billionaire recruits a team of international operatives for a bold and bloody mission to take down a brutal dictator.",
                "${imageUrl}lnWkyG3LLgbbrIEeyl5mK5VRFe4.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )

        movies.add(
            MovieModel(
                "546554",
                "Knives Out",
                "When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.",
                "${imageUrl}pThyQovXQrw2m0s9x82twj48Jq4.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )

        movies.add(
            MovieModel(
                "290859",
                "Terminator: Dark Fate",
                "Decades after Sarah Connor prevented Judgment Day, a lethal new Terminator is sent to eliminate the future leader of the resistance. In a fight to save mankind, battle-hardened Sarah Connor teams up with an unexpected ally and an enhanced super soldier to stop the deadliest Terminator yet.",
                "${imageUrl}vqzNJRH4YyquRiWxCCOH0aXggHI.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )



        return movies
    }

    fun generateDummyTvShow(): ArrayList<TvShowModel> {

        val tvShow = ArrayList<TvShowModel>()

        tvShow.add(
            TvShowModel(
                "82856",
                "The Mandalorian",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "${imageUrl}BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )


        tvShow.add(
            TvShowModel(
                "60625",
                "Rick and Morty",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "${imageUrl}8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg",
                "December 2, 2013",
                "8.9"
            )
        )


        tvShow.add(
            TvShowModel(
                "1412",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "${imageUrl}gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Febuary 05, 2020",
                "8.2"
            )
        )



        tvShow.add(
            TvShowModel(
                "60735",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "${imageUrl}wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "June 05, 2020",
                "8.2"
            )
        )



        tvShow.add(
            TvShowModel(
                "71641",
                "4 Blocks",
                "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple",
                "${imageUrl}jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
                "May 05, 2020",
                "8.2"
            )
        )


        tvShow.add(
            TvShowModel(
                "63639",
                "The Expanse",
                "A thriller set two hundred years in the future following the case of a missing young woman who brings a hardened detective and a rogue ship's captain together in a race across the solar system to expose the greatest conspiracy in human history.",
                "${imageUrl}wikmaI7OVqmq2O9HfknkzxX6Ygu.jpg",
                "September 05, 2020",
                "8.2"
            )
        )



        tvShow.add(
            TvShowModel(
                "1622",
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "${imageUrl}KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "July 20, 2020",
                "8.2"
            )
        )


        tvShow.add(
            TvShowModel(
                "68507",
                "His Dark Materials",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                "${imageUrl}xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )


        tvShow.add(
            TvShowModel(
                "44217",
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "${imageUrl}ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )



        tvShow.add(
            TvShowModel(
                "2734",
                "Law & Order: Special Victims Unit",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "${imageUrl}6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                "Feb 05, 2020",
                "8.2"
            )
        )


        return tvShow
    }

}