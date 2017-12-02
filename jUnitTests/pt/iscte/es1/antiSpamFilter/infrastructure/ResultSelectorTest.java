package pt.iscte.es1.antiSpamFilter.infrastructure;

import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;
import pt.iscte.es1.antiSpamFilter.domain.ResultWeightComposite;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies.LeisureStrategy;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

public class ResultSelectorTest {
	private final String qualityPoints = "33.0 33.0 \n" +
		"108.0 5.0\n" +
		"2.0 2.0\n" +
		"1.0 7.0\n";
	private final String selectedMatch = "-3.9075417432984114 -1.5377466958149153 1.8768719687891444 -3.094138509177834 -3.3803476296141053 4.492796687089397 -0.2925436887108912 4.08291792925799 -4.749140848078989 -4.741527038773487 4.7241136361844696 4.260929196144856 1.5657248781933877 4.19294959960053 4.583918565703113 -0.05533666987505104 -1.211391173243419 -0.5672267564184015 2.1282356926946253 1.0014361330978225 0.5321469465210384 -0.2103390279125641 0.9146441362463333 1.6169316218887717 -2.5657960421399206 -3.3961940728128526 3.650827796874646 0.9855822760805992 -3.6027390107719004 1.0842144595190266 3.568575456478987 -0.6028077315927796 0.8131083713140042 -2.505953324214661 2.7311178525666495 1.6646872453440178 4.892050443233204 4.853384311905918 -0.7153960151279399 -3.3164193536069817 -0.9853517532711651 3.708803391504027 -2.255401893667899 -4.678053058889281 -4.114641168696159 -0.041417031830629725 2.551057490080381 -1.1896037910830448 -0.8087411667416555 4.724332113138992 -4.669672191416867 -3.665250345042246 -2.544636167331613 -2.1757269047276386 0.5702716845597413 3.6298143461809165 -4.177110917391255 2.530370913810536 2.7447257818419004 4.03086346055143 -2.3698814033941797 0.9809515626794676 0.3160562903280262 -0.33987265944389033 -4.220616841654183 2.872405565175187 -1.5003358093526495 3.2088082131935636 1.1799734654908454 4.868025098417226 3.1918693832135663 0.12082716792568338 1.8136442580003687 4.538979431521717 3.0426767820325704 -3.1072737300507134 1.344689224862475 0.5918822110751858 -4.702486696859647 3.674839656368322 0.618401569921307 0.6393596472986971 3.7370938596675654 -1.6079398810850698 -1.1219438354191178 -1.1655185034516213 3.8121615809507894 -4.801774042028331 4.079383938731507 -1.6831284801378974 0.899916441026777 2.2363546801806518 3.292829696111543 -2.3922382665747213 2.5015335220768478 2.397661664376044 4.857720986829973 -4.156784479329755 4.15524253122843 -3.944508203505932 3.688796735495666 -0.9508079768231816 1.857024928984857 -3.403992340807871 -0.06562272254775792 -1.4609218638377506 0.8610568453179468 -2.0243258453611013 -0.9532170735063037 2.953771923904701 1.8746103361060937 -1.4937171856242957 -1.7505629370171558 -2.533652411213918 0.8671981792412176 -3.3985333229244397 0.3714172231408499 -2.7622075422701964 -2.0186714080603583 2.3143528472204986 1.0690491528395025 -2.662749159687011 4.362315388750911 3.1542110602357667 -0.43453899952495334 -1.970325428404387 3.117162617185558 3.8906495428715786 -3.787153014068705 2.5857196841546575 2.06762610198584 -2.1628744031626024 2.418519628286518 4.583193919910743 1.4801286753676752 1.4512277299803875 -0.2253481226114804 3.046303158383859 -0.46471229064376907 4.997400072653042 -4.407055503786692 -3.2915934628512824 1.9423851652402222 -0.5115228798697267 1.4692781753509898 3.1859346077155095 -0.19080218878702926 4.155601823906176 -0.010259033659638206 4.9155864294974325 1.6451745578040295 -0.7081987584689733 2.238246881902186 1.3338678694340835 1.2060315062653046 -3.5272965837193504 -4.622707001658776 -4.032510718852542 2.7804112439413666 1.8785714133971263 -4.752774643651105 -2.1449039475750684 -0.5256681183388299 -1.2749550230206106 3.665719401779411 -0.9647469192058331 -1.9332726491267813 1.6556749205481616 3.9080009215324747 3.7125632864761258 -3.910536155590499 -0.6424566455484833 0.7096692568588292 0.15324964042543954 2.9277881758609645 1.6319642170590445 3.0578041287262145 3.3634831345257794 -3.057753611540047 3.6854609137064003 -0.9776245929519973 -4.8318024115474785 4.880691754047872 3.501923263760439 1.9287935996242664 -1.5649245393682132 -1.820929319595419 -2.1849533366255383 -3.716226062603485 3.87785296992414 3.1422635383101074 -1.5791386698101038 0.04051350711084467 1.1250365639260131 -1.5486749069128103 3.3533615538577806 -2.9744965989321583 0.5545843850969678 3.9610591433907665 -3.208051645862635 -0.3518622260164843 -1.2279497264894381 -0.6680714648853758 -2.5223982396825937 -2.6244037472388912 -1.450849338252299 -0.31711289923793107 -2.114597140062182 0.8793134669296343 3.541658465342655 -1.8432041867410929 -4.565770668851172 -4.754323673948235 -0.8300093997969604 2.0576650980299016 2.610146305137249 -1.8075869309128443 3.6727989195831228 0.049038983447710294 -4.961771886488159 0.8434723546321354 -2.4666625034393928 2.795422021854872 -4.7417644727629735 -0.7109860055524377 -4.769675714410688 -1.6162786507194857 -4.757208420148556 4.893463088115356 0.5794719318848323 -3.1207428824643415 2.54238619156739 0.11341033356638253 -4.191183444967481 -4.549023964110851 3.3497589023540524 4.448461543692053 4.950424117240495 -3.356929255102771 0.7468985394660477 2.0719766094493135 4.1330067125014 -2.424698746774067 -2.0688895558701317 -1.3250412128193312 4.924439356853782 -2.8781232229690055 4.312301243539077 0.10519024671298638 2.0001129954960195 1.4488449961619647 -2.9681647857304427 -0.5049696110817354 3.102519334873861 -4.317488058906672 1.6740207203723925 2.7451285840806774 -2.4905872769138027 0.9364898798684509 3.7244830201880017 4.467626469573295 -0.9576507137480661 1.4425526692501753 -3.324606578895497 -2.208746682706124 1.0243547921453136 -3.8894447462937864 3.5857134918742783 1.7920516850768822 3.5349272589836698 2.485251555224524 -3.6246690910795722 -0.2627099186328232 -2.1874675057009996 -1.2270772527579377 1.1651547859663882 -0.18354803206525272 -1.280794546273838 1.3783079333421542 -1.4290503440579516 -2.417976713669411 3.8088215918315207 1.2224038072274954 -4.983115483201046 -4.065883465215317 -3.269943951760792 2.0042838559686125 4.855481105521809 4.1568117547183405 3.447002337113771 -2.4748423111615727 3.3638452008017623 2.2504116049327196 2.074875292458164 3.210477825564425 -2.4682291223229713 0.6026419221870158 1.0051741735918132 -0.9540269789124753 4.965506388868327 -2.096843946633613 -4.56586179285763 -2.7139316231846444 1.2016815460846084 2.1295285453646624 -0.5897224439310547 -1.5017713632289356 2.2590870396710736 0.4192513451589104 -0.9187843397503812 3.774342323756361 2.351713154285653 3.691077666656925 2.947034683058255 -0.7296601421780613 -3.6553511070680345 0.9903793275239945 -3.383234516571566 1.62781626557312 2.7551482254217685 -3.2929776983475123 2.816620375189002 -0.7134892306919234 -4.115624188764472 1.0505797048944077 2.213013798621171 -4.785931226296972 -4.988957786819118 0.4665449283016514 -3.0096204136904534 1.1595972573376248 4.486349625875677 0.03852040075498575 -2.9367658806496264 2.180090736332435 \n";
	private final String qualityPointsWeights = "-4.123353998017738 3.742675189917154 4.2341148622858835 4.068875404592276 1.436990184720437 -3.5678689757767037 -0.621618781659528 -0.7694173767638448 3.0069169040314776 4.0078448703997225 1.1881214854990088 -3.2220758630349975 2.5917992581862297 0.8570049130584065 2.0705500423369427 0.7439334755015272 1.502720317640419 0.9078227708982567 2.8040583639691965 -3.637973101905323 1.2992912034499868 -0.38489783312802395 -1.5477813648476246 0.25887698207219234 2.176440947755517 4.130210739636487 -4.284558390207956 1.9965188137721315 -2.951342942117275 -2.822996904758593 -3.3611944317463074 4.8143298210798875 3.3058585408773276 -4.170934139051211 4.346531221002785 -2.623384831266342 4.4982881458889725 1.1199881171976145 -1.2119771080928263 -3.6509052606827694 1.0535083600086903 -1.7828126462022862 4.357355960102945 1.7076788376260321 2.5367983026661314 -0.24885335818022547 -3.3124597942290235 2.8254894550969514 3.9369418318358846 -3.184247578927535 2.9228432936576576 3.5891636556844553 4.992605174757212 3.141743014875498 -1.663089609904671 -0.936941459589046 4.6702070472219415 -2.005886286768166 2.2913702090540653 4.5500470725524185 2.5623829955335644 2.4556471473016197 -1.7809285088572393 0.2901803033401755 -4.803146663863531 1.9325681268758066 -4.431955794746738 1.493564873878964 -1.9712983448171348 4.954647909386509 -3.543263539526947 3.9213822190352037 3.234780715688238 4.640915487768023 -3.831971440007349 -0.19094010148798102 -0.6628394854687265 4.563456533920039 4.548980994874338 -2.4318244853916973 1.1532386559023111 -4.834388003984098 4.885223479951131 1.408757908452313 -2.396665759033405 0.37721610051538335 -2.1166523414098473 3.7912256600064733 0.5022947383905212 -3.381662048493892 1.0216182049095046 4.447282166857532 -0.3801606606912058 -2.0056718558351507 -2.2812283082535356 4.601664676340393 -1.984922123925469 2.158786797970534 -3.477378425400829 3.981720104778608 -3.066165739947848 1.0574097035191308 -2.1653532388656895 1.2193644724304908 1.4031157005347836 -0.43550864700401704 4.563689034997923 -4.669945846541519 -3.934730270053378 2.8851907343650858 3.5413401550713868 -2.904020569275022 -4.234927799245115 0.5204991052301056 -3.466445438658589 4.38576930331134 -3.0638050397204664 0.578169897657185 3.2474703885859846 4.710571120357571 3.929203208177979 3.2617079012426444 3.8593827178942526 1.6464504349881492 -0.9185616105180667 -3.519166046288017 -4.72073977758048 -0.854368818390637 -3.450159941324401 -0.2993248301067979 3.619150896371277 -0.7858198789986464 -2.2890275847831765 4.383259442472996 -4.852721863403156 3.951308777537472 0.27821299137525735 0.6737138693097497 -0.6134232704645068 2.5255527000293494 4.1942747376033225 0.3449065753211231 0.9558783563341553 -2.6943384372386747 4.827928712766594 -4.561913409304434 1.2539007963690327 1.0691125656588207 -1.9630002824583137 0.523416642263193 -2.8493441762050473 -1.0212624662552807 2.2554137605381586 4.0531110785746165 1.477839391826267 4.848033887295831 -0.07972829875900445 0.8912284479092358 -0.7007766922625578 1.1083041028612861 4.275635934896254 3.777989421256933 4.694017850094379 -4.154148581362698 -2.1270875516842547 -3.777176633562487 -4.899369900698134 -1.7049665257849647 -4.066583301583359 3.1191727555236817 4.145198225795099 -3.746945082431986 1.146916775221472 -1.764586439617566 -0.6812729727104285 -4.975040007048616 -3.5493535603485027 -0.3399238019948969 -2.1313864643135636 -2.287638920294326 -1.133513311249433 -4.73411533292208 2.8347964216184227 0.9270198126290019 0.5697164701178794 0.30595204745486804 1.2877202046297231 -1.659525608127895 3.020937450806116 -1.6331942160421775 -1.4852425047465632 -3.1265143533385253 -3.7865216167085802 -4.923259931786607 -4.846316175892737 -3.2158497138275246 -2.1282100530057466 1.2043205152375371 -0.9137420498123845 -2.5688891027931646 -3.2179549822394824 -1.9192988145698884 0.2819056202249346 -4.931629265638091 0.3443576593522568 3.640728260393942 4.7209663755911855 -2.3292072762357074 -1.0932556762829937 1.8016512416688624 3.0289088373043835 4.494450208035547 -1.5258544785206904 1.3845719656593856 0.5173785533842068 4.843568717385242 -1.0184611896833573 3.332605392297358 -2.0451077895182634 1.3458762583627486 -2.521631714165097 -3.408956767419596 -3.420553348584705 -2.0692399044808383 4.292224182765366 3.6886462875119754 -3.382735407483153 1.6597059936627723 -4.477093051929504 -3.204483390985675 -4.113651780702446 0.5760712405240884 -2.936282612026898 3.8318281830207894 3.9404745392467593 -3.17867572683547 3.7550083994678207 2.0630265643176573 -2.3512956603865263 -4.592539640492572 2.245749540276192 3.5409299037186663 -3.89255353980422 4.240379605335551 -2.3279524770983095 2.736499495640073 -3.6534400992678373 0.25659414326482555 2.357274318599246 1.7588688768831187 -3.5820657669505773 -0.13429114096503625 -0.6033398928496219 2.666553298502378 -0.8555584720674023 -0.7163039389177408 -4.7900969402734175 -0.44941121330423783 -1.9408924607108902 1.7970649132304821 2.928560654070786 -4.694324269014781 -3.121200491940499 4.515604670941954 2.8306827269894796 -0.6957665608529551 -4.7663173607412475 -3.248543360099332 -3.056643640812078 2.5495967124101018 -3.686846508763523 -4.1951495356898185 2.404526712440706 -3.691581430434736 -3.4090928929869535 3.6419170594960057 2.2086729048378153 -1.2913357787824262 3.8562483793688322 -0.2824323004466436 2.5566102559800132 1.3384166405191325 -2.5942832747762843 1.0265106282019607 3.1878995466173023 4.492038560395091 3.4385366831878095 0.35804996667791933 0.7950458784258707 -4.615937139438001 -0.548628398198062 -2.3146078563836903 -4.098129005075041 -1.612484525276697 3.74229845143447 -2.362235031135068 0.4341486003404027 0.15219873816574037 0.010886951798383393 -1.916271250375444 -3.3812054590779472 -4.680206888289325 2.480710360069139 0.996639424163245 -2.181395519042387 0.4764647540736542 3.972570598128083 -3.021698822129806 -1.5572611888892371 4.969991596851681 -2.078820539052776 -0.9155737799491064 3.168855452771542 -2.7327478665731366 -4.545021314126157 -0.07440824220406039 -4.629007325635689 4.63924326981649 0.348249676074448 -1.590030730917238 0.8680121234892946 2.134631186852265 -2.0933970696858717 -1.050824443829911 -4.109390580992468 4.03915840339967 -1.3386010044261276 -3.5338267935548506 -2.4893924045886306 -2.6829374489179827 4.1256035011323515 -4.8757741711413605 0.6844489706135173 1.891988549655217 -0.19696714222541623 \n" +
		"-3.7566820886317 -3.3117707961461376 1.7986649367473175 -2.045031490936773 -3.3247214545440738 -4.060345841650847 -3.6913771845707375 0.777330887339521 0.15906770742740584 -2.353293043983423 -3.656902586791151 -2.014567856647181 -0.13663434352403048 1.3168018141602182 -2.5287921550196946 -2.736738049483891 2.5463661868922713 3.117881003036363 -0.8569686025188066 4.3400752170735615 1.358945423527766 0.694542829929671 -0.9534416483901156 -1.347966813454077 3.4946770532476172 4.837932101570926 2.739506562860317 -3.4983194487124303 -0.42945007715826566 3.469901084215042 0.8158201490815644 4.15330667502411 2.1398117981616096 2.7476575249767654 2.165509402558291 -0.3208059162999035 -4.681552921286777 -3.9966859563849577 -2.7521883541892036 0.3112082797584641 -3.850625061109473 3.7034571381889663 -0.2785120136932129 1.554140838967819 1.033525486305365 -3.3529362761560377 -1.7376081766407205 -4.618337008422192 -2.6754580886513324 0.2157839545101936 2.947277746900112 -2.5372904287379914 1.0139187154999005 -0.3511733399404502 2.1941470062307875 -2.4444428738193356 -3.6740176151474913 -4.964626471777125 -3.0767490025057684 2.958819995279022 1.9971812843850048 6.602392170362847E-4 -1.6607935397976692 2.223038033587578 2.0317428448823343 -0.09243610801753821 4.533501576626602 -4.796517464170162 -4.1941710247431665 3.8037347378478774 -1.0319998280671037 -4.025736794893703 3.534896963039367 -0.6849802776464848 0.8238805255229043 -2.8742342212002345 -1.675944392781962 1.2191259593442876 3.1867729449029376 -2.4680873618771813 -4.711218450650643 2.5291710268362024 0.0876895007897236 0.8006813300926492 1.5634396088022253 1.574979825804883 -2.469336231415679 -1.6892231338318595 -1.5923718292874112 -0.20931944271442848 -3.188148522156254 -1.0106974680505996 -1.9245478678214436 2.7003612108193265 0.6711675016203458 -2.2002688381875233 3.630399513471943 0.9392525527511806 4.116364479601064 -3.7135755664248338 -0.5314215122564336 -0.9439002161424215 2.1207864103051834 1.4614118216096275 3.9556500361363565 -2.68690469699407 -4.180144125879609 3.821081987005643 3.2878682914388566 -4.267054325817702 -2.6823116203750095 1.3449034760213694 -2.5447818724138505 0.2383467830303747 0.764046626508267 3.955236323838431 -4.985277249159495 -2.112652647579021 3.973182690911294 2.1067532590387303 3.2745623877576193 1.9287824807381257 0.5299407391000575 3.9259433270450863 4.4447353019226945 -2.3148362818475143 -2.9351765966594776 -2.364379443087401 -3.672441142617667 -2.883888982575887 4.178548743684212 -0.12368482694631666 -1.798603965801835 3.3236228661503766 2.357958013358682 -4.024120711702368 -1.3064342211917612 -3.7688259244819102 -3.8510388496641657 -4.779411016188289 2.643472316341895 -2.7981575746358676 0.8385294176082496 2.745481211704478 -1.775426167934885 3.4563633111767693 -4.053416526658188 -0.21138606542863947 3.9238792637502673 2.8102186533684304 -1.0600556914178694 -4.256605246127302 -2.6297503051998214 -4.53043675878949 0.7543435978830075 -1.3049292664103818 4.457851491566654 2.409003427192542 1.946824596749451 -4.058067600186446 -2.2713102473407143 -0.8000628222794681 4.599685417635699 2.3787332548633184 -2.441769832845311 0.06637594073095165 0.45844776131482945 2.5592771893273367 -1.669307669719815 -0.5841351725864445 0.2101627107074444 1.7570888477947406 4.495675985986319 -2.873674507067552 2.294693989954615 2.407522180308261 -1.8346356991022192 -0.5346527866405157 2.1904421921805515 4.941626136332452 0.7416203712923499 -0.32334491730475534 4.727310405534288 -3.78846456470162 -4.045126798557318 0.989188057699721 2.6535309772770344 4.459104670814609 0.651771407401255 0.08742077905504164 4.419305546284743 -2.9060367259835718 2.686155570232387 0.9272684006588081 2.939040746179521 -3.5758821472373947 2.34798062313207 -2.809883044877375 4.955284173924198 -1.0783185574033602 4.907398669106669 -3.3617781192665843 -2.4487005513206017 -4.391735982607054 -2.943711002187718 2.1679227268869425 4.345540717104102 4.709011934152436 -0.7028977907128304 2.4528279729520532 -1.0498748506333069 2.8512347844152437 -0.3410392350218574 -0.2726814322468307 -2.6132838652464594 -2.2269869002567386 1.5135386584230917 3.481432472724661 4.327072777027146 3.759134891331957 -0.28167966840973424 -3.3786119325973307 0.26183702824115684 0.8361574315982256 4.832418226562357 4.995709020402563 -2.0000328876377385 0.1353480587315996 4.7302018563974055 2.0951186487217486 -2.7374530721702994 0.7717111396799066 -3.419422518960875 -1.7949261402056118 -4.441464763610576 -1.9107438272489108 -0.13517312021288674 -2.6769911646886833 2.0163418023138693 -0.8128759825166689 2.873498805604177 -4.251933044793818 -1.6060511320886062 -0.7318701640281322 0.9529205173316058 3.4092993850972437 -1.8983770789337662 1.5407472199263594 4.664476599050971 2.7190105426642264 -0.2549093680238359 4.157499434570573 -0.02511995525435573 0.021138788279955634 -3.56661961601941 -0.012813668360603714 0.6132400363670945 1.5502589104410323 -4.105641959522243 -1.1046797718652601 4.810806162947335 -0.7750036526762916 -0.6976817121308256 -2.353394216111264 4.78184866019296 1.570352198176593 -1.3541868081513755 -0.45475633529761694 -2.4907860253116985 1.5966325176602547 3.4502355153553133 4.493296543295635 -0.33433108801607236 -3.4416153430459504 -2.281503484151537 0.2743083529301078 -2.1677701363751845 4.174944867707929 -2.935527704745885 -2.441182606361666 -2.4327535449526074 -3.8850975306981352 4.187712197871015 4.380361627349888 1.3350924248834195 2.3299949410383123 -1.9257604247792082 0.6450249376488024 -4.179111864201954 -0.830358652035736 -1.3928998929992065 -0.3854743398401608 3.737766692730691 0.11553579490183541 3.6316371088282775 1.2423045027365855 3.87576195339779 0.4464183418597205 -0.0556921027883579 1.7440197260613424 -4.153514283418679 -3.8236542785381267 3.1601797565453538 4.848311787639265 2.7453323858963437 3.2054891856563987 3.963071623933873 2.8006514560007734 -4.216969406400656 4.780686510338407 -0.14636112864760342 -0.6174587662719482 -0.16906063146676775 4.083683311704323 2.184522196166432 2.960289356880457 -2.220437363402362 4.048194962560435 -1.991985084295298 4.892031926543503 4.627336707695278 -1.6473690845087896 -4.939473323086916 -2.501369747339066 -0.7031795008759456 -4.056478083291114 -3.9305483176466116 -3.75647678850627 3.3180000888354204 -3.5846075202717778 -4.508121302618998 2.768571316215991 0.7234927326896923 -4.893968851866273 -2.2190943419693845 \n" +
		selectedMatch +
		"-4.308251271669464 0.08445564107566472 1.09833517942584 -2.487541655969819 2.3991150252043267 3.3862584821595014 1.7445247979809881 2.32657202150582 4.654347933883569 1.5159788422879563 -2.7819956058350614 -0.709736749846746 3.5600262798732203 2.649016355729489 2.82426046281282 -0.6493594440651451 -1.7183479971384088 1.540662943883154 2.3870546995524675 0.731097571803784 -2.922673495411278 2.982613024211057 4.224180695084325 3.7017432018024845 4.87036354524437 2.194947609574016 0.15740506801924248 1.4437833014999244 1.1993772272677017 1.1858761049664235 -1.3969636964615306 -4.208884409320419 -4.055168432453319 -0.6208935467005405 0.8852733143098614 1.6812312947767598 -4.642011042981552 0.13878298902778852 4.572689949066454 -1.7411546677128764 -2.392346195029549 2.2145172438057292 -1.1410907431343342 -2.2504997638879463 -4.687945103620103 -3.415890088350948 4.79173015269266 -4.543276340163526 2.3376676571362767 4.6891424878471994 -2.499331042829036 -4.464857523525643 3.523727891586608 3.6386955073979497 -1.5189174892580573 -2.4467123522125322 -1.069358088673277 -2.400971833873208 3.4637420812965125 -1.7163510782307787 -1.28754628251198 2.292129771778945 -4.568068478845738 1.6438699682981657 0.7779792392525477 1.293636927010839 2.943844912162027 -2.7381970464619587 1.0321729369337405 -1.349317333114458 1.0762634819322772 4.748462759080116 -4.8212541018948425 2.232825970829971 -3.973807825661184 -3.5632388508792157 1.8170049527940453 -3.815714248236064 4.690344536239152 -3.9197846743521936 -1.1196617506225715 -1.1367162308675303 -2.611940768661174 4.804667727899027 -0.6592747741713776 4.734376381120901 -4.698554408269125 -1.5326300590038078 -2.4569008265450587 4.822236224230702 4.177247444682159 2.5016181811505973 4.535349649101773 0.5404195666913578 2.3681267730300615 1.311456136247199 -0.6758022067441729 -0.02376306505227266 4.386765968322884 -0.6702153999322729 3.1141375736977643 4.042919056555288 0.8043939544893766 -0.03544934897446517 -4.723915830688991 2.3028520993988844 1.5460417991497337 -3.1684860873610186 3.0443262098358197 4.720729820993018 2.418039957645756 -1.091583193912009 1.754831559999137 -3.8769462478600616 4.04896934548475 -2.4557870404427327 -1.1850914313093042 4.720484915778833 -1.6665172630038407 -1.3816620079470532 2.5336788779034976 4.692134176196644 -0.06088412084936845 -2.4311680076167983 -3.555230617348103 -4.464049672887739 -0.5305867869981986 -1.5055456571115067 0.8170866065574378 -2.3430483144656256 0.4723901842221112 1.0847580654344995 -4.3235123324454285 4.495115479174771 4.641565001445823 0.7434175115233117 3.8506159028191416 -3.6126761777676633 -1.722509455485275 -2.0560652524918965 -1.6988776237281034 0.3128409806324619 -1.6390807804080296 2.593175670976761 -4.67805205106155 2.68937689732235 -1.5655554761730919 -2.022984919948474 4.059292414905229 -0.5331348929094135 2.897655351670563 -3.276299843400572 3.248352310981023 -1.091332601983984 3.2915506460184822 -1.8556346745227916 -1.9361391600549127 -2.345008882638866 1.6811626207379957 2.2964674374425282 3.5459168228184055 -0.0691145431209943 4.341036291586626 1.1647282948815372 -3.8104593083788862 3.091261445403479 3.606423370976712 1.4655086519112706 1.975854830400876 0.08795449006597345 -3.0262557724976236 -3.2456386598292832 -2.6252701768774758 0.8989348308290275 0.3170804487902936 -1.2471616159361965 -0.33680088822806376 0.4773378707270295 -3.5668400736995998 -0.8905395924416606 4.28089225541299 0.358077123695665 1.0355020984173668 3.171076480332811 -0.11680459818506606 4.702367713274814 -4.210908281343983 -3.2353182643360157 -3.745206806195994 -0.7344206322086935 2.010155987889936 -4.5239256404860795 -3.689290617955173 3.541404622966331 -0.7486824999358452 3.4605760969505504 1.0389053105165482 2.165414419673036 0.15060625358888302 -4.567641650800685 -4.572040606116698 1.5536452001220908 -3.3564907837096336 1.1527458991309745 4.175295143028576 1.706946080190141 2.9204443596583634 -3.525795592772866 -1.7141150640658065 1.1293854828845076 -2.642251971355287 -1.2044622643979563 -2.4516959913114675 4.8891019212517115 -0.9415337550784697 -2.354846591436204 -0.6855322514484463 1.9925562982741987 4.609943693428622 3.033085395401722 1.0113347440840172 -1.5517765879618919 -2.8015889015864923 -4.80121752622309 2.377313236537332 -3.336659431908686 3.3164684489023184 -0.569487878048399 3.543058893163165 0.14740430389954629 1.9575707344011075 -1.256457213885409 -3.234190296186709 4.888688017879883 -2.5876791932319176 -4.621891900343611 2.8783530448947303 -4.7748677062486085 -0.43541695463988805 -0.6855217442381942 -1.6718275196481605 2.5485345787874722 4.931127976895036 -1.6987075208373357 -4.9407190438788575 -0.23895619237766574 -2.403275014215278 -0.7453638511582241 3.428690340704609 3.0455463263807303 1.6673046319606648 -4.69082489921505 -2.1485684239991243 -4.7833944914452555 -4.102988512751597 -3.7110176739004643 -4.067516544918148 1.6861929365690997 -3.259325866968447 2.914529536581333 4.470925956909891 0.9085354252514009 -1.7585796975550627 -3.3263659285191682 -0.08891248439337929 -3.9390315089594896 -4.1161289596543105 -2.0420110401978233 -3.4325948201447676 3.6727767841272456 -3.1734517899466095 4.803223528072481 3.264174396283936 -2.068429501679969 0.7728324459290832 3.3692786228625327 -2.091686752153933 -3.5231665076473364 4.148016268475841 -0.8407984821322732 -0.047275787173678196 4.6328475867804695 -4.77930684748705 4.898296676079211 2.709785510653271 0.2382853464359247 -0.66006620287747 -3.0181012883695777 1.0389590663700545 0.49733291969782023 1.5553973762848372 -2.5511432642059075 -4.9405437981261535 -4.392709352667414 -1.454265324526074 2.5491529567460933 -2.7716476313518945 1.3101704359192734 3.001337900120612 2.147902965549161 -1.6235020385887333 3.9708909438682394 1.390698544320803 -3.555684666508827 -1.8909739556891667 -2.697749257280754 0.3853364724177659 -2.216987058420016 -3.645795087368752 -0.5630691246254029 -1.8735322573029798 -4.3035482802703084 -4.216152056661521 2.6893675642072923 3.987277495764051 2.740997054636373 -0.6695682052249659 -3.9445971565636793 4.517875768655749 -2.8244543455596283 3.0905415357191384 2.265205834916266 3.8557072359399758 -3.9195887159811384 2.5312027315464425 -2.964966716555306 4.042396830805124 -0.8744790062830461 -0.8948789590999038 -1.9440232622228546 4.4087118620878964 -1.1292081276776877 -4.8671992791555425 -4.787080702719942 -0.5274716700536342 \n";

	@Test
	public void whenUsingLeisureStrategyShouldReturnSelectedMatch() throws IOException {
		final List<PositiveNegativeSet> positiveNegativeSets = new AntiSpamFileReader<>(new PositiveNegativeParser())
			.readFile(new StringReader(qualityPoints));
		final ExperimentResultWeightsParser experimentResultWeightsParser = new ExperimentResultWeightsParser();
		final List<ResultWeightComposite> weightComposites = new AntiSpamFileReader<>(experimentResultWeightsParser)
			.readFile(new StringReader(qualityPointsWeights));
		final ResultSelector rs = new ResultSelector(new LeisureStrategy());
		final ResultWeightComposite match = rs.selectFromResults(positiveNegativeSets, weightComposites);
		final ResultWeightComposite shouldMatch = experimentResultWeightsParser.parse(selectedMatch);
		assertEquals(shouldMatch, match);
	}
}