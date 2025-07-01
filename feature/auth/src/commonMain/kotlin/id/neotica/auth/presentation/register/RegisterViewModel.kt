package id.neotica.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import id.neotica.domain.ApiResult
import id.neotica.auth.domain.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepo: AuthRepository,
): ViewModel() {

    private var _doesUserExist = MutableSharedFlow<Boolean>()
    val doesUserExist = _doesUserExist.asSharedFlow()

    private var _loading = MutableSharedFlow<Boolean>()
    val loading = _loading.asSharedFlow()

    private var _registerResult = MutableSharedFlow<String>()
    val registerResult = _registerResult.asSharedFlow()

    fun register(username: String, email: String, password: String, callback: () -> Unit) = viewModelScope.launch {
        _loading.emit(true)
        val result = authRepo.register(username, email, password)
        when(result) {
            is ApiResult.Loading -> _loading.emit(true)
            is ApiResult.Success -> {
                _loading.emit(false)
                _registerResult.emit(result.data.toString())
                callback()
            }
            is ApiResult.Error -> {
                _loading.emit(false)
                Logger.e { "registervm: ${result.errorMessage.toString()}" }
                _registerResult.emit(result.errorMessage.toString())
            }
        }
    }

    fun checkUsername(username: String) = viewModelScope.launch {
        _doesUserExist.emit(false)
        val user = authRepo.isUsernameExist(username)
        when(user) {
            is ApiResult.Loading -> {
                _doesUserExist.emit(false)
            }
            is ApiResult.Success -> {
                user.data?.let { _doesUserExist.emit(it) }
            }
            is ApiResult.Error -> {
                _doesUserExist.emit(false)
            }
        }
    }

    val blockedWord = listOf(
        "nigga", "nigger", "nigguh", "neoverse", "neotica",
        "aa5u", "a5uu", "aasu", "ajg", "alay", "ampas", "anus", "anjeng", "anjing", "anjir", "antek", "asem",
        "asing", "autis", "ayamkampus", "awuk", "b1ch", "bab1", "babi", "bacot", "bajang", "bajingan",
        "banci", "bandot", "bangkai", "bangsat", "bani", "bani kotak", "bego", "bejat", "bencong", "berak",
        "berengsek", "bgsd", "bgst", "bispak", "bisu", "bisyar", "bjat", "bods", "bokep", "bokong", "bong",
        "bodoh", "budek", "burik", "burit", "butt", "cacat", "cawk", "cct", "cebong",
        "celeng", "cerewet", "cipok", "cl1t", "cntz", "cocot", "congor", "crut", "crot", "cukimak",
        "cums", "cunt", "culun", "cungkring", "cupu", "dick", "d1ck", "d4mn", "damn", "dancok", "dongok", "dunguk",
        "dungu", "dyke", "edan", "eewe", "elek", "entot", "f4rt", "fags", "fagz", "fcuk", "fukr",
        "fukk", "fuck", "gay", "gays", "gayz", "gawk", "gauk", "gawu", "gelud", "gembel", "gembrot", "gendut",
        "genjit", "genjek", "geblek", "gila", "gblk", "g1la", "germo", "goblok", "hina", "homo",
        "idiot", "jablay", "jamban", "jancok", "jancuk", "jemb", "jembud", "jembut", "jijik", "jilmek", "jiss",
        "jizz", "jlek", "kacrut", "kafir", "kancut", "kampang", "kampret", "kampungan", "kent", "kere", "kejam",
        "keparat", "kimak", "kimax", "kimboknya", "kl1t", "knob", "kntl", "kont0l", "kontol", "k0nt0l",
        "k0ntol", "koreng", "kotl", "krempeng", "kunti", "kunyuk", "lengser", "leec", "lesbi",
        "lgbt", "lonte", "mani", "mampus", "mat1", "mbut", "me2k", "mek1", "meki", "memek", "mesum", "modar",
        "monyet", "mucikari", "mulyono", "munafik", "n1gr", "najis", "naz1", "ncuk", "ndas", "nenen", "nete", "ngaceng",
        "ngewe", "ngewek", "ngentot", "nista", "noob", "nt1l", "ntut", "nthu", "nyepong", "onta", "p1ss",
        "panasbung", "panastak", "pantat", "pantek", "pasukan nasi", "pcun", "pecun", "pekok", "peler", "pel1",
        "pelacur", "peju", "p3ju", "perek", "picek", "pler", "porno", "puki", "pukimak", "pus1", "puss", "pups",
        "rejin", "rezim", "sampah", "sange", "sarap", "sepong", "seponk", "s3pong", "serbet", "seks",
        "silit", "sinting", "sipit", "sitip", "slut", "songong", "sompret", "sontoloyo", "stfu", "tai", "taek",
        "taik", "taplak", "temp", "tempik", "tepos", "terkutuk", "tetek", "titit", "t1ts", "titt", "tobrut",
        "toket", "tol", "tolol", "transgendertuyul", "twat", "udik", "up1l", "utek", "vags", "wank", "wdus",
        "xhamster.com", "itil"
    ).toSet().toList()
}