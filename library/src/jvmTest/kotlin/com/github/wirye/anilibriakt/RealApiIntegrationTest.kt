package com.github.wirye.anilibriakt

import com.github.wirye.anilibriakt.model.UserProfileFields
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class RealApiIntegrationTest {

    private val client = AniLibriaClient(tokenProvider = { "your auth token" })

    @Test
    fun `real login with wrong password returns InvalidCredentialsException`(): Unit = runTest {
        val result = client.auth.login("test", "test")

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isFailure)
    }


    @Test
    fun `get profile works corretly with invalid token`(): Unit = runTest {
        val result = client.auth.getProfile(UserProfileFields.entries.toList())

        println(">>> Result isSuccess: ${result.isSuccess}")
        println(">>> Result: $result")
        println(">>> Exception type: ${result.exceptionOrNull()?.javaClass?.simpleName}")
        println(">>> Exception message: ${result.exceptionOrNull()?.message}")

        assertTrue(result.isFailure)
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
        val result = client.releases.getRelease(id = 9420)

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
}
