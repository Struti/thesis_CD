import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class MockTableService {

    constructor() { }

    mockData = [
        {
            "id": 1,
            "first_name": "Orrin",
            "last_name": "Kelf",
            "email": "okelf0@nhs.uk"
        }, {
            "id": 2,
            "first_name": "Mata",
            "last_name": "Lundbeck",
            "email": "mlundbeck1@google.com.au"
        }, {
            "id": 3,
            "first_name": "Van",
            "last_name": "Breddy",
            "email": "vbreddy2@weibo.com"
        }, {
            "id": 4,
            "first_name": "Cletus",
            "last_name": "Buddle",
            "email": "cbuddle3@vkontakte.ru"
        }, {
            "id": 5,
            "first_name": "Teri",
            "last_name": "Eltone",
            "email": "teltone4@reuters.com"
        }, {
            "id": 6,
            "first_name": "Spence",
            "last_name": "MacIlriach",
            "email": "smacilriach5@uiuc.edu"
        }, {
            "id": 7,
            "first_name": "Wain",
            "last_name": "Schwieso",
            "email": "wschwieso6@mapy.cz"
        }, {
            "id": 8,
            "first_name": "Johan",
            "last_name": "Wyley",
            "email": "jwyley7@jiathis.com"
        }, {
            "id": 9,
            "first_name": "Rob",
            "last_name": "Partington",
            "email": "rpartington8@oracle.com"
        }, {
            "id": 10,
            "first_name": "Rodie",
            "last_name": "Eilhermann",
            "email": "reilhermann9@auda.org.au"
        }, {
            "id": 11,
            "first_name": "Constanta",
            "last_name": "Gudd",
            "email": "cgudda@comsenz.com"
        }, {
            "id": 12,
            "first_name": "Ivan",
            "last_name": "Fattore",
            "email": "ifattoreb@bbc.co.uk"
        }, {
            "id": 13,
            "first_name": "Cly",
            "last_name": "Casari",
            "email": "ccasaric@jalbum.net"
        }, {
            "id": 14,
            "first_name": "Mannie",
            "last_name": "Pursey",
            "email": "mpurseyd@blinklist.com"
        }, {
            "id": 15,
            "first_name": "Colline",
            "last_name": "Shankster",
            "email": "cshankstere@issuu.com"
        }, {
            "id": 16,
            "first_name": "Gloriana",
            "last_name": "Lademann",
            "email": "glademannf@chron.com"
        }, {
            "id": 17,
            "first_name": "Gardner",
            "last_name": "Treswell",
            "email": "gtreswellg@meetup.com"
        }, {
            "id": 18,
            "first_name": "Gilburt",
            "last_name": "O' Molan",
            "email": "gomolanh@shinystat.com"
        }, {
            "id": 19,
            "first_name": "Tobin",
            "last_name": "Balser",
            "email": "tbalseri@yolasite.com"
        }, {
            "id": 20,
            "first_name": "Gherardo",
            "last_name": "Franceschelli",
            "email": "gfranceschellij@ox.ac.uk"
        }, {
            "id": 21,
            "first_name": "Dal",
            "last_name": "Lawtie",
            "email": "dlawtiek@icq.com"
        }, {
            "id": 22,
            "first_name": "Aldo",
            "last_name": "Totman",
            "email": "atotmanl@histats.com"
        }, {
            "id": 23,
            "first_name": "Ode",
            "last_name": "Mathivet",
            "email": "omathivetm@wordpress.com"
        }, {
            "id": 24,
            "first_name": "Bride",
            "last_name": "Middlehurst",
            "email": "bmiddlehurstn@github.io"
        }, {
            "id": 25,
            "first_name": "Gertrudis",
            "last_name": "Harnwell",
            "email": "gharnwello@ning.com"
        }, {
            "id": 26,
            "first_name": "Chloette",
            "last_name": "Eakens",
            "email": "ceakensp@dion.ne.jp"
        }, {
            "id": 27,
            "first_name": "Gerardo",
            "last_name": "Geraldo",
            "email": "ggeraldoq@dot.gov"
        }, {
            "id": 28,
            "first_name": "Basilio",
            "last_name": "Penfold",
            "email": "bpenfoldr@biblegateway.com"
        }, {
            "id": 29,
            "first_name": "Kelcie",
            "last_name": "Kupka",
            "email": "kkupkas@blinklist.com"
        }, {
            "id": 30,
            "first_name": "Kacy",
            "last_name": "Eustis",
            "email": "keustist@github.io"
        }, {
            "id": 31,
            "first_name": "Averell",
            "last_name": "Pulfer",
            "email": "apulferu@sakura.ne.jp"
        }, {
            "id": 32,
            "first_name": "Graham",
            "last_name": "Mantz",
            "email": "gmantzv@epa.gov"
        }, {
            "id": 33,
            "first_name": "Ario",
            "last_name": "Ummfrey",
            "email": "aummfreyw@ovh.net"
        }, {
            "id": 34,
            "first_name": "Katharine",
            "last_name": "Twyning",
            "email": "ktwyningx@mtv.com"
        }, {
            "id": 35,
            "first_name": "Clayborn",
            "last_name": "Quittonden",
            "email": "cquittondeny@who.int"
        }, {
            "id": 36,
            "first_name": "Julina",
            "last_name": "Lippett",
            "email": "jlippettz@netscape.com"
        }, {
            "id": 37,
            "first_name": "Carree",
            "last_name": "Mateos",
            "email": "cmateos10@mediafire.com"
        }, {
            "id": 38,
            "first_name": "Lucita",
            "last_name": "Baiden",
            "email": "lbaiden11@microsoft.com"
        }, {
            "id": 39,
            "first_name": "Sharline",
            "last_name": "Heather",
            "email": "sheather12@auda.org.au"
        }, {
            "id": 40,
            "first_name": "Katherina",
            "last_name": "Tatlow",
            "email": "ktatlow13@scribd.com"
        }, {
            "id": 41,
            "first_name": "Lynsey",
            "last_name": "Haime",
            "email": "lhaime14@ning.com"
        }, {
            "id": 42,
            "first_name": "Saloma",
            "last_name": "Nicholas",
            "email": "snicholas15@mtv.com"
        }, {
            "id": 43,
            "first_name": "Willa",
            "last_name": "Robeiro",
            "email": "wrobeiro16@oaic.gov.au"
        }, {
            "id": 44,
            "first_name": "Abbye",
            "last_name": "Pittle",
            "email": "apittle17@ycombinator.com"
        }, {
            "id": 45,
            "first_name": "Donielle",
            "last_name": "Carillo",
            "email": "dcarillo18@time.com"
        }, {
            "id": 46,
            "first_name": "Templeton",
            "last_name": "Lowdeane",
            "email": "tlowdeane19@biglobe.ne.jp"
        }, {
            "id": 47,
            "first_name": "Kania",
            "last_name": "Tweddell",
            "email": "ktweddell1a@springer.com"
        }, {
            "id": 48,
            "first_name": "Jocelyne",
            "last_name": "Lumox",
            "email": "jlumox1b@toplist.cz"
        }, {
            "id": 49,
            "first_name": "Kandy",
            "last_name": "Hymers",
            "email": "khymers1c@about.me"
        }, {
            "id": 50,
            "first_name": "Gamaliel",
            "last_name": "Dighton",
            "email": "gdighton1d@usda.gov"
        }, {
            "id": 51,
            "first_name": "Fraser",
            "last_name": "Cerie",
            "email": "fcerie1e@technorati.com"
        }, {
            "id": 52,
            "first_name": "Redd",
            "last_name": "Lavin",
            "email": "rlavin1f@photobucket.com"
        }, {
            "id": 53,
            "first_name": "Suzanna",
            "last_name": "Athow",
            "email": "sathow1g@photobucket.com"
        }, {
            "id": 54,
            "first_name": "Mei",
            "last_name": "Orteaux",
            "email": "morteaux1h@msn.com"
        }, {
            "id": 55,
            "first_name": "Anjanette",
            "last_name": "Brake",
            "email": "abrake1i@exblog.jp"
        }, {
            "id": 56,
            "first_name": "Benetta",
            "last_name": "Huby",
            "email": "bhuby1j@google.pl"
        }, {
            "id": 57,
            "first_name": "Reggie",
            "last_name": "Iwaszkiewicz",
            "email": "riwaszkiewicz1k@yandex.ru"
        }, {
            "id": 58,
            "first_name": "Harley",
            "last_name": "Miko",
            "email": "hmiko1l@creativecommons.org"
        }, {
            "id": 59,
            "first_name": "Gilberte",
            "last_name": "Larkby",
            "email": "glarkby1m@dell.com"
        }, {
            "id": 60,
            "first_name": "Livy",
            "last_name": "MacGall",
            "email": "lmacgall1n@i2i.jp"
        }, {
            "id": 61,
            "first_name": "Marigold",
            "last_name": "Skamell",
            "email": "mskamell1o@blogspot.com"
        }, {
            "id": 62,
            "first_name": "Cicely",
            "last_name": "Spellicy",
            "email": "cspellicy1p@disqus.com"
        }, {
            "id": 63,
            "first_name": "Brittan",
            "last_name": "Garden",
            "email": "bgarden1q@techcrunch.com"
        }, {
            "id": 64,
            "first_name": "Mady",
            "last_name": "Crady",
            "email": "mcrady1r@ustream.tv"
        }, {
            "id": 65,
            "first_name": "Laurella",
            "last_name": "Prettyman",
            "email": "lprettyman1s@netscape.com"
        }, {
            "id": 66,
            "first_name": "Paula",
            "last_name": "Nelson",
            "email": "pnelson1t@goo.gl"
        }, {
            "id": 67,
            "first_name": "Kim",
            "last_name": "Romaint",
            "email": "kromaint1u@google.de"
        }, {
            "id": 68,
            "first_name": "Glenine",
            "last_name": "Michallat",
            "email": "gmichallat1v@hc360.com"
        }, {
            "id": 69,
            "first_name": "Agneta",
            "last_name": "Nyssen",
            "email": "anyssen1w@feedburner.com"
        }, {
            "id": 70,
            "first_name": "Isa",
            "last_name": "Brinkworth",
            "email": "ibrinkworth1x@bigcartel.com"
        }, {
            "id": 71,
            "first_name": "Chip",
            "last_name": "Whyley",
            "email": "cwhyley1y@gov.uk"
        }, {
            "id": 72,
            "first_name": "Silvia",
            "last_name": "Petroulis",
            "email": "spetroulis1z@npr.org"
        }, {
            "id": 73,
            "first_name": "Gates",
            "last_name": "Whetton",
            "email": "gwhetton20@vistaprint.com"
        }, {
            "id": 74,
            "first_name": "Maurie",
            "last_name": "Longman",
            "email": "mlongman21@ftc.gov"
        }, {
            "id": 75,
            "first_name": "Sigismond",
            "last_name": "Brittlebank",
            "email": "sbrittlebank22@cdbaby.com"
        }, {
            "id": 76,
            "first_name": "Raina",
            "last_name": "Hassell",
            "email": "rhassell23@seesaa.net"
        }, {
            "id": 77,
            "first_name": "Bev",
            "last_name": "Gamage",
            "email": "bgamage24@dropbox.com"
        }, {
            "id": 78,
            "first_name": "Armand",
            "last_name": "Varns",
            "email": "avarns25@alexa.com"
        }, {
            "id": 79,
            "first_name": "Daffie",
            "last_name": "Galway",
            "email": "dgalway26@slashdot.org"
        }, {
            "id": 80,
            "first_name": "Koo",
            "last_name": "Spray",
            "email": "kspray27@cbc.ca"
        }, {
            "id": 81,
            "first_name": "Masha",
            "last_name": "MacKaig",
            "email": "mmackaig28@rediff.com"
        }, {
            "id": 82,
            "first_name": "Giorgi",
            "last_name": "Triggs",
            "email": "gtriggs29@google.ru"
        }, {
            "id": 83,
            "first_name": "Pryce",
            "last_name": "Cowins",
            "email": "pcowins2a@g.co"
        }, {
            "id": 84,
            "first_name": "Nadean",
            "last_name": "Dumini",
            "email": "ndumini2b@ebay.co.uk"
        }, {
            "id": 85,
            "first_name": "Desmund",
            "last_name": "Halshaw",
            "email": "dhalshaw2c@moonfruit.com"
        }, {
            "id": 86,
            "first_name": "Malynda",
            "last_name": "Peplaw",
            "email": "mpeplaw2d@wisc.edu"
        }, {
            "id": 87,
            "first_name": "Rand",
            "last_name": "Yokelman",
            "email": "ryokelman2e@hostgator.com"
        }, {
            "id": 88,
            "first_name": "Lilith",
            "last_name": "Yushachkov",
            "email": "lyushachkov2f@slashdot.org"
        }, {
            "id": 89,
            "first_name": "Viviana",
            "last_name": "Biford",
            "email": "vbiford2g@google.de"
        }, {
            "id": 90,
            "first_name": "Marketa",
            "last_name": "Barck",
            "email": "mbarck2h@github.io"
        }, {
            "id": 91,
            "first_name": "Brand",
            "last_name": "Bazylets",
            "email": "bbazylets2i@163.com"
        }, {
            "id": 92,
            "first_name": "Anderson",
            "last_name": "Ligoe",
            "email": "aligoe2j@house.gov"
        }, {
            "id": 93,
            "first_name": "Shurlock",
            "last_name": "Heyfield",
            "email": "sheyfield2k@ed.gov"
        }, {
            "id": 94,
            "first_name": "Chrissie",
            "last_name": "Callum",
            "email": "ccallum2l@usda.gov"
        }, {
            "id": 95,
            "first_name": "Klement",
            "last_name": "Cardiff",
            "email": "kcardiff2m@cam.ac.uk"
        }, {
            "id": 96,
            "first_name": "Jewelle",
            "last_name": "Mawby",
            "email": "jmawby2n@rambler.ru"
        }, {
            "id": 97,
            "first_name": "Byran",
            "last_name": "Kneesha",
            "email": "bkneesha2o@ucoz.ru"
        }, {
            "id": 98,
            "first_name": "Haleigh",
            "last_name": "Howell",
            "email": "hhowell2p@blogger.com"
        }, {
            "id": 99,
            "first_name": "Remy",
            "last_name": "Wellum",
            "email": "rwellum2q@howstuffworks.com"
        }, {
            "id": 100,
            "first_name": "Norton",
            "last_name": "Bradder",
            "email": "nbradder2r@networksolutions.com"
        }]
}