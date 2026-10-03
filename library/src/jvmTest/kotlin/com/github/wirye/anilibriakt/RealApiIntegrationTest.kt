package com.github.wirye.anilibriakt

import com.github.wirye.anilibriakt.api.CollectionsApi
import com.github.wirye.anilibriakt.model.Collections
import com.github.wirye.anilibriakt.model.EpisodeTimecode
import com.github.wirye.anilibriakt.model.TitleGenres
import com.github.wirye.anilibriakt.model.UserProfileFields
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class RealApiIntegrationTest {

    private val client = AniLibriaClient(tokenProvider = { "erAhpXI3vhcUw7fdxv8yErtkC2HbHCV0" }, passkeyProvider = { "" })

    @Test
    fun `real login with wrong password returns InvalidCredentialsException`(): Unit = runTest {
        val result = client.account.auth.login("test", "test")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isFailure)
    }


    @Test
    fun `get profile test`(): Unit = runTest {
        val result = client.account.auth.getProfile(UserProfileFields.entries.toList())

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `search test`(): Unit = runTest {
        val result = client.search.search("звездное дитя", 1)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get offers test`(): Unit = runTest {
        val result = client.search.getOffers("звездное дитя", limit = 4)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `recommended releases test`(): Unit = runTest {
        val result = client.releases.recommended(limit = 4, releaseId = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.size} $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `latest releases test`(): Unit = runTest {
        val result = client.releases.latest(limit = 4)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.size} $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get release test`(): Unit = runTest {
        val result = client.releases.getRelease(id = 9433)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get release members test`(): Unit = runTest {
        val result = client.releases.getReleaseMembers(id = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get episode user watched timecodes test`(): Unit = runTest {
        val result = client.releases.episodes.getEpisodeUserWatchedTimecodes(episodeId = "98f5e3f7-8a1f-4724-bd95-894db467bd3b")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get episode test`(): Unit = runTest {
        val result = client.releases.episodes.getEpisode(episodeId = "98f5e3f7-8a1f-4724-bd95-894db467bd3b")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get release episodes user watched timecodes test`(): Unit = runTest {
        val result = client.releases.getReleaseEpisodesUserWatchedTimecodes(id = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get random releases test`(): Unit = runTest {
        val result = client.releases.getRandomReleases(limit = 4)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.size} $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get many releases test`(): Unit = runTest {
        val result = client.releases.getManyReleases(ids = listOf(9420, 9433))

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.data?.size} $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get schedule test`(): Unit = runTest {
        val result = client.releases.schedule.getSchedule()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get week schedule test`(): Unit = runTest {
        val result = client.releases.schedule.getWeekSchedule()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get favorites ids test`(): Unit = runTest {
        val result = client.account.favorites.getIdsOfFavoriteReleases()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get favorites releases test`(): Unit = runTest {
        val result = client.account.favorites.getFavoriteReleases()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `add release to favorites test`(): Unit = runTest {
        val result = client.account.favorites.addReleaseToFavorites(releaseId = 9433)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `remove release from favorites test`(): Unit = runTest {
        val result = client.account.favorites.removeReleaseFromFavorites(releaseId = 9433)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get ads test`(): Unit = runTest {
        val result = client.ads.getAvailableVastAds()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get available user age ratings test`(): Unit = runTest {
        val result = client.account.favorites.getAvailableUserAgeRatings()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get available user genres test`(): Unit = runTest {
        val result = client.account.favorites.getAvailableUserGenres()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get available user sorting test`(): Unit = runTest {
        val result = client.account.favorites.getAvailableUserSoring()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get available user types test`(): Unit = runTest {
        val result = client.account.favorites.getAvailableUserTypes()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get available user years test`(): Unit = runTest {
        val result = client.account.favorites.getAvailableUserYears()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get history test`(): Unit = runTest {
        val result = client.account.history.getHistory()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get timecodes test`(): Unit = runTest {
        val result = client.account.history.getTimecodes()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `update timecodes test`(): Unit = runTest {
        val result = client.account.history.updateTimecodes(
            timecodes = listOf(EpisodeTimecode(episodeId="95bc84a0-789e-11ec-ae92-0242ac120002", timecode=0f, isWatched=false))
        )

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `delete timecodes test`(): Unit = runTest {
        val result = client.account.history.deleteTimecodes(
            timecodes = listOf("95bc84a0-789e-11ec-ae92-0242ac120002"))

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get collections releases test`(): Unit = runTest {
        val result = client.account.collections.getReleasesIds()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get collection releases test`(): Unit = runTest {
        val result = client.account.collections.getCollectionReleases(typeOfCollection = Collections.WATCHED)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `add release to collection test`(): Unit = runTest {
        val result = client.account.collections.addReleaseToCollection(
            listOf(
                CollectionsApi.AddCollectionRequest(
                    releaseId = 9433,
                    typeOfCollection = Collections.WATCHED
                )
            )
        )

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `delete release to collection test`(): Unit = runTest {
        val result = client.account.collections.deleteReleaseToCollection(
            listOf(
                CollectionsApi.AddCollectionRequest(
                    releaseId = 9433,
                    typeOfCollection = Collections.WATCHED
                )
            )
        )

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get available genres test`(): Unit = runTest {
        val result = client.releases.genres.getAvailableGenres()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get genre test`(): Unit = runTest {
        val result = client.releases.genres.getGenre(genre = TitleGenres.FANTASY)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get random genres test`(): Unit = runTest {
        val result = client.releases.genres.getRandomGenres(limit = 4)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.size}, $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get genre releases test`(): Unit = runTest {
        val result = client.releases.genres.getGenreReleases(genre = TitleGenres.FANTASY, page = 1, limit = 4)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.data?.size}, $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get franchises test`(): Unit = runTest {
        val result = client.releases.franchises.getFranchises()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get franchise test`(): Unit = runTest {
        val result = client.releases.franchises.getFranchise(id = "9f7649af-aeb9-42e8-8167-98bfa4eba36e")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get random franchises test`(): Unit = runTest {
        val result = client.releases.franchises.getRandomFranchises(limit = 4)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.size}, $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get franchise releases test`(): Unit = runTest {
        val result = client.releases.franchises.getFranchisesForRelease(releaseId = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.size}, $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get torrents test`(): Unit = runTest {
        val result = client.torrents.getTorrents(page = 1, limit = 2)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: ${result.getOrNull()?.data?.size}, $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get torrent by id test`(): Unit = runTest {
        val result = client.torrents.getTorrent(idOrHash = "40201")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get torrent by hash test`(): Unit = runTest {
        val result = client.torrents.getTorrent(idOrHash = "47d4ffd6218b45aeb0b5606b8856a912cbf39c1b")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get torrents for release test`(): Unit = runTest {
        val result = client.torrents.getTorrentsForRelease(releaseId = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get torrent rss test`(): Unit = runTest {
        val result = client.torrents.getTorrentRss(limit = 1)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get torrent rss for release`(): Unit = runTest {
        val result = client.torrents.getTorrentRssForRelease(releaseId = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `get my release rating test`(): Unit = runTest {
        val result = client.releases.ratings.getMyReleaseRating(releaseId = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `set my release rating test`(): Unit = runTest {
        val result = client.releases.ratings.setMyReleaseRating(releaseId = 9420, rating = 5)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `delete my release rating test`(): Unit = runTest {
        val result = client.releases.ratings.deleteMyReleaseRating(releaseId = 9420)

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `logout test`(): Unit = runTest {
        val result = client.account.auth.logout()

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isSuccess)
    }
}
