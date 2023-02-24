import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fabrica {

	private Random random;

	private String[] premiosInstantaneos;
	private String[] paisesParticipantes;
	private int randomInt;
	private int randomPos;
	private int lugaresPorPais;
	private String[] listadoDeMundialesTop10;
	private Figurita[] aColeccionar;
	private FiguTOP10[] coleccionTOP10;
	private String[] codigosPromocionales;
	private ArrayList<String> codigosUsados;
	private Map<String, String[]> balonYPaisPorMundialTop10;
	private Map<String, Integer> ranking;

	Fabrica() {
		random = new Random(System.currentTimeMillis());
		lugaresPorPais = 12;
		randomInt = random.nextInt(9999);
		paisesParticipantes = generarPaisesClasificados();
		listadoDeMundialesTop10 = generarListadoDeMundiales();
		codigosPromocionales = generarCodigos();
		randomPos = random.nextInt(codigosPromocionales.length);

		codigosUsados = new ArrayList<>();
		balonYPaisPorMundialTop10 = generarPaisesPorMundial();
		ranking = generarRanking();
		premiosInstantaneos = generarPremiosParaSorteoInstantaneo();
		coleccionTOP10 = generarFigusTOP10();
		aColeccionar = generarFigus(); // nuetra coleccion
	}

	////////////////////////////////////////////////////////////////////////
	// NOTA: Deben implementar los siguientes metodos segun su modelo //
	// Aprovechando la informacion referida al mundial facilitada //
	// por la catedra. //
	// Solamente deberian deberan usar las variables de clases //
	// y el metodo "calcularValorBase" para saber que valor base //
	// tendrá una figurita en particula. //
	////////////////////////////////////////////////////////////////////////

	// Los inicializo como album o como su tipo??
	Album crearAlbumWeb() {
		int codigo = randomInt;
		Album albumWeb = new Web(codigo, "Web");
		albumWeb.setId(codigo);
		albumWeb.generarSecciones(paisesParticipantes, aColeccionar, lugaresPorPais);
		((Web) albumWeb).generarCodigoPromo(codigosPromocionales, randomPos);
		return albumWeb;
	}

	Album crearAlbumExtendido() {
		int codigo = randomInt;
		Album albumExtendido = new Extendido(codigo, "Extendido");
		albumExtendido.setId(codigo);
		albumExtendido.generarSecciones(paisesParticipantes, aColeccionar, lugaresPorPais);
		((Extendido) albumExtendido).generarSeccionesTOP10(coleccionTOP10); // upcasting
		return albumExtendido;
	}

	Album crearAlbumTradicional() {
		int codigo = randomInt;
		Album albumTradicional = new Tradicional(randomInt, "Tradicional");
		albumTradicional.setId(codigo);
		albumTradicional.generarSecciones(paisesParticipantes, aColeccionar, lugaresPorPais);
		return albumTradicional;
	}

	///////////////////////////////////////////////////////
	///////////// METODOS FACILITADOS POR LA CATEDRA //////
	///////////////////////////////////////////////////////

	// Dado el pais y numero de jugador de una figurita calcula
	// cual es su valor base simbobilo.
	private int calcularValorBase(String pais, int numero) {
		return ranking.get(pais) + numero;
	}

	private String[] generarPremiosParaSorteoInstantaneo() {
		return new String[] {
				"  Una pelota", "1 Sobre Gratis", "Una camiseta"
		};
	}

	private String[] generarPaisesClasificados() {
		return new String[] {
				"Alemania", "Arabia Saudí", "Argentina", "Australia",
				"Brasil", "Bélgica", "Camerún", "Canadá",
				"Costa Rica", "Croacia", "Dinamarca", "Ecuador",
				"España", "Estados Unidos", "Francia", "Gales",
				"Ghana", "Inglaterra", "Irán", "Japón",
				"Marruecos", "México", "Países Bajos", "Polonia",
				"Portugal", "Qatar", "República de Corea", "Senegal",
				"Serbia", "Suiza", "Túnez", "Uruguay"
		};
	}

	private String[] generarListadoDeMundiales() {
		return new String[] {
				"España '82", "México '86", "Italia '90", "Estados Unidos '94",
				"Francia '98", "Corea del Sur y Japón '02", "Alemania '06",
				"Sudáfrica '10", "Brasil '14", "Rusia '18" };
	}

	private Map<String, String[]> generarPaisesPorMundial() {
		Map<String, String[]> ret = new HashMap<>();
		ret.put("España '82", new String[] { "Italia", "Brasil" });
		ret.put("México '86", new String[] { "Argentina", "Alemania" });
		ret.put("Italia '90", new String[] { "Italia", "Alemania" });
		ret.put("Estados Unidos '94", new String[] { "Brasil", "Italia" });
		ret.put("Francia '98", new String[] { "Brasil", "Croacia" });
		ret.put("Corea del Sur y Japón '02", new String[] { "Alemania", "Brasil" });
		ret.put("Alemania '06", new String[] { "Francia", "Italia" });
		ret.put("Sudáfrica '10", new String[] { "Uruguay", "Países Bajos" });
		ret.put("Brasil '14", new String[] { "Argentina", "Alemania" });
		ret.put("Rusia '18", new String[] { "Croacia", "Bélgica" });
		return ret;
	}

	private Map<String, Integer> generarRanking() {
		Map<String, Integer> ret = new HashMap<>();
		ret.put("Brasil", 1);
		ret.put("Bélgica", 2);
		ret.put("Argentina", 3);
		ret.put("Francia", 4);
		ret.put("Inglaterra", 5);
		ret.put("Italia", 6);
		ret.put("España", 7);
		ret.put("Países Bajos", 8);
		ret.put("Portugal", 9);
		ret.put("Dinamarca", 10);
		ret.put("Alemania", 11);
		ret.put("Croacia", 12);
		ret.put("México", 13);
		ret.put("Uruguay", 14);
		ret.put("Suiza", 15);
		ret.put("Estados Unidos", 16);
		ret.put("Colombia", 17);
		ret.put("Senegal", 18);
		ret.put("Gales", 19);
		ret.put("Irán", 20);
		ret.put("Serbia", 21);
		ret.put("Marruecos", 22);
		ret.put("Perú", 23);
		ret.put("Japón", 24);
		ret.put("Suecia", 25);
		ret.put("Polonia", 26);
		ret.put("Ucrania", 27);
		ret.put("República de Corea", 28);
		ret.put("Chile", 29);
		ret.put("Túnez", 30);
		ret.put("Costa Rica", 31);
		ret.put("Nigeria", 32);
		ret.put("Rusia", 33);
		ret.put("Austria", 34);
		ret.put("República Checa", 35);
		ret.put("Hungría", 36);
		ret.put("Argelia", 37);
		ret.put("Australia", 38);
		ret.put("Egipto", 39);
		ret.put("Escocia", 40);
		ret.put("Canadá", 41);
		ret.put("Noruega", 42);
		ret.put("Camerún", 43);
		ret.put("Ecuador", 44);
		ret.put("Turquía", 45);
		ret.put("Mali", 46);
		ret.put("Paraguay", 47);
		ret.put("Costa de Marfil", 48);
		ret.put("República de Irlanda", 49);
		ret.put("Qatar", 50);
		ret.put("Arabia Saudí", 51);
		ret.put("Grecia", 52);
		ret.put("Rumanía", 53);
		ret.put("Burkina Faso", 54);
		ret.put("Eslovaquia", 55);
		ret.put("Finlandia", 56);
		ret.put("Venezuela", 57);
		ret.put("Bosnia y Herzegovina", 58);
		ret.put("Irlanda del Norte", 59);
		ret.put("Panamá", 60);
		ret.put("Ghana", 61);
		ret.put("Islandia", 62);
		ret.put("Eslovenia", 63);
		ret.put("Jamaica", 64);
		ret.put("Macedonia del Norte", 65);
		ret.put("Albania", 66);
		ret.put("Sudáfrica", 67);
		ret.put("Irak", 68);
		ret.put("Montenegro", 69);
		ret.put("Emiratos Árabes Unidos", 70);
		ret.put("Cabo Verde", 71);
		ret.put("Bulgaria", 72);
		ret.put("RD del Congo", 73);
		ret.put("El Salvador", 74);
		ret.put("Omán", 75);
		ret.put("Israel", 76);
		ret.put("Uzbekistán", 77);
		ret.put("Georgia", 78);
		ret.put("RP China", 79);
		ret.put("Honduras", 80);
		ret.put("Gabón", 81);
		ret.put("Bolivia", 82);
		ret.put("Guinea", 83);
		ret.put("Jordania", 84);
		ret.put("Bahréin", 85);
		ret.put("Curaçao", 86);
		ret.put("Haití", 87);
		ret.put("Zambia", 88);
		ret.put("Uganda", 89);
		ret.put("Siria", 90);
		ret.put("Benín", 91);
		ret.put("Luxemburgo", 92);
		ret.put("Armenia", 93);
		ret.put("Palestina", 94);
		ret.put("República Kirguisa", 95);
		ret.put("Vietnam", 96);
		ret.put("Bielorrusia", 97);
		ret.put("Guinea Ecuatorial", 98);
		ret.put("Líbano", 99);
		ret.put("Congo", 100);
		ret.put("Kenia", 101);
		ret.put("Madagascar", 102);
		ret.put("Mauritania", 103);
		ret.put("Trinidad y Tobago", 104);
		ret.put("Nueva Zelanda", 105);
		ret.put("India", 106);
		ret.put("Kosovo", 107);
		ret.put("Tayikistán", 108);
		ret.put("Estonia", 109);
		ret.put("Chipre", 110);
		ret.put("Tailandia", 111);
		ret.put("RDP de Corea", 112);
		ret.put("Kazajstán", 113);
		ret.put("Mozambique", 114);
		ret.put("Namibia", 115);
		ret.put("Guinea-Bissáu", 116);
		ret.put("Sierra Leona", 117);
		ret.put("Guatemala", 118);
		ret.put("Angola", 119);
		ret.put("Libia", 120);
		ret.put("Níger", 121);
		ret.put("Islas Feroe", 122);
		ret.put("Azerbaiyán", 123);
		ret.put("Malaui", 124);
		ret.put("Zimbabue", 125);
		ret.put("Gambia", 126);
		ret.put("Togo", 127);
		ret.put("Sudán", 128);
		ret.put("Comoras", 129);
		ret.put("Tanzania", 130);
		ret.put("Antigua y Barbuda", 131);
		ret.put("República Centroafricana", 132);
		ret.put("Filipinas", 133);
		ret.put("Letonia", 134);
		ret.put("Turkmenistán", 135);
		ret.put("Islas Salomón", 136);
		ret.put("Ruanda", 137);
		ret.put("Etiopía", 138);
		ret.put("Surinam", 139);
		ret.put("San Cristóbal y Nieves", 140);
		ret.put("Burundi", 141);
		ret.put("Nicaragua", 142);
		ret.put("Esuatini", 143);
		ret.put("Lituania", 144);
		ret.put("Hong Kong", 145);
		ret.put("Malasia", 146);
		ret.put("Lesoto", 147);
		ret.put("Botsuana", 148);
		ret.put("Kuwait", 149);
		ret.put("Liberia", 150);
		ret.put("Andorra", 151);
		ret.put("Indonesia", 152);
		ret.put("República Dominicana", 153);
		ret.put("Maldivas", 154);
		ret.put("Yemen", 155);
		ret.put("Afganistán", 156);
		ret.put("Chinese Taipei", 157);
		ret.put("Myanmar", 158);
		ret.put("Papúa Nueva Guinea", 159);
		ret.put("Singapur", 160);
		ret.put("Nueva Caledonia", 161);
		ret.put("Tahití", 162);
		ret.put("Fiyi", 163);
		ret.put("Vanuatu", 164);
		ret.put("Sudán del Sur", 165);
		ret.put("Barbados", 166);
		ret.put("Cuba", 167);
		ret.put("Malta", 168);
		ret.put("Bermudas", 169);
		ret.put("Puerto Rico", 170);
		ret.put("Guyana", 171);
		ret.put("Santa Lucía", 172);
		ret.put("Granada", 173);
		ret.put("Moldavia", 174);
		ret.put("Nepal", 175);
		ret.put("Belice", 176);
		ret.put("Camboya", 177);
		ret.put("San Vicente y las Granadinas", 178);
		ret.put("Montserrat", 179);
		ret.put("Mauricio", 180);
		ret.put("Chad", 181);
		ret.put("Macao", 182);
		ret.put("Mongolia", 183);
		ret.put("Dominica", 184);
		ret.put("Bután", 185);
		ret.put("Santo Tomé y Príncipe", 186);
		ret.put("Laos", 187);
		ret.put("Samoa Estadounidense", 188);
		ret.put("Islas Cook", 189);
		ret.put("Brunéi Darussalam", 190);
		ret.put("Samoa", 191);
		ret.put("Bangladesh", 192);
		ret.put("Yibuti", 193);
		ret.put("Pakistán", 194);
		ret.put("Islas Caimán", 195);
		ret.put("Liechtenstein", 196);
		ret.put("Tonga", 197);
		ret.put("Timor Oriental", 198);
		ret.put("Seychelles", 199);
		ret.put("Eritrea", 200);
		ret.put("Aruba", 201);
		ret.put("Bahamas", 202);
		ret.put("Somalia", 203);
		ret.put("Gibraltar", 204);
		ret.put("Guam", 205);
		ret.put("Turcas y Caicos", 206);
		ret.put("Sri Lanka", 207);
		ret.put("Islas Vírgenes Estadounidenses", 208);
		ret.put("Islas Vírgenes Británicas", 209);
		ret.put("Anguila", 210);
		ret.put("San Marino", 211);
		return ret;
	}

	// Nuestros Metodos
	public String premios(String tipoAlbum) {
		if (tipoAlbum == "Tradicional") {
			return "Una Pelota";
		}
		if (tipoAlbum == "Web") {
			int pos = random.nextInt(codigosPromocionales.length);
			return codigosPromocionales[pos];
		}
		return "Una pelota y una camiseta";
	}

	private Figurita[] generarFigus() {
		Figurita[] figus = new Figurita[] {
				new Figurita(1, "uno", "Alemania", 1),
				new Figurita(2, "dos", "Alemania", 2),
				new Figurita(3, "tres", "Alemania", 3),
				new Figurita(4, "cuatro", "Alemania", 4),
				new Figurita(5, "cinco", "Alemania", 5),
				new Figurita(6, "seis", "Alemania", 6),
				new Figurita(7, "siete", "Alemania", 7),
				new Figurita(8, "ocho", "Alemania", 8),
				new Figurita(9, "nueve", "Alemania", 9),
				new Figurita(10, "diez", "Alemania", 10),
				new Figurita(11, "once", "Alemania", 11),
				new Figurita(12, "doce", "Alemania", 12),
				new Figurita(13, "trece", "Arabia Saudí", 1),
				new Figurita(14, "catorce", "Arabia Saudí", 2),
				new Figurita(15, "quince", "Arabia Saudí", 3),
				new Figurita(16, "dieciseis", "Arabia Saudí", 4),
				new Figurita(17, "diecisiete", "Arabia Saudí", 5),
				new Figurita(18, "dieciocho", "Arabia Saudí", 6),
				new Figurita(19, "diecinueve", "Arabia Saudí", 7),
				new Figurita(20, "veinte", "Arabia Saudí", 8),
				new Figurita(21, "veintiuno", "Arabia Saudí", 9),
				new Figurita(22, "veintidos", "Arabia Saudí", 10),
				new Figurita(23, "veintitres", "Arabia Saudí", 11),
				new Figurita(24, "veinticuatro", "Arabia Saudí", 12),
				new Figurita(25, "veinticinco", "Argentina", 1),
				new Figurita(26, "veintiseis", "Argentina", 2),
				new Figurita(27, "veintisiete", "Argentina", 3),
				new Figurita(28, "veintiocho", "Argentina", 4),
				new Figurita(29, "veintinueve", "Argentina", 5),
				new Figurita(30, "treinta", "Argentina", 6),
				new Figurita(31, "treinta y uno", "Argentina", 7),
				new Figurita(32, "treinta y dos", "Argentina", 8),
				new Figurita(33, "treinta y tres", "Argentina", 9),
				new Figurita(34, "treinta y cuatro", "Argentina", 10),
				new Figurita(35, "treinta y cinco", "Argentina", 11),
				new Figurita(36, "treinta y seis", "Argentina", 12),
				new Figurita(37, "treinta y siete", "Australia", 1),
				new Figurita(38, "treinta y ocho", "Australia", 2),
				new Figurita(39, "treinta y nueve", "Australia", 3),
				new Figurita(40, "cuarenta", "Australia", 4),
				new Figurita(41, "cuarenta y uno", "Australia", 5),
				new Figurita(42, "cuarenta y dos", "Australia", 6),
				new Figurita(43, "cuarenta y tres", "Australia", 7),
				new Figurita(44, "cuarenta y cuatro", "Australia", 8),
				new Figurita(45, "cuarenta y cinco", "Australia", 9),
				new Figurita(46, "cuarenta y seis", "Australia", 10),
				new Figurita(47, "cuarenta y siete", "Australia", 11),
				new Figurita(48, "cuarenta y ocho", "Australia", 12),
				new Figurita(49, "cuarenta y nueve", "Brasil", 1),
				new Figurita(50, "cincuenta", "Brasil", 2),
				new Figurita(51, "cincuenta y uno", "Brasil", 3),
				new Figurita(52, "cincuenta y dos", "Brasil", 4),
				new Figurita(53, "cincuenta y tres", "Brasil", 5),
				new Figurita(54, "cincuenta y cuatro", "Brasil", 6),
				new Figurita(55, "cincuenta y cinco", "Brasil", 7),
				new Figurita(56, "cincuenta y seis", "Brasil", 8),
				new Figurita(57, "cincuenta y siete", "Brasil", 9),
				new Figurita(58, "cincuenta y ocho", "Brasil", 10),
				new Figurita(59, "cincuenta y nueve", "Brasil", 11),
				new Figurita(60, "sesenta", "Brasil", 12),
				new Figurita(61, "sesenta y uno", "Bélgica", 1),
				new Figurita(62, "sesenta y dos", "Bélgica", 2),
				new Figurita(63, "sesenta y tres", "Bélgica", 3),
				new Figurita(64, "sesenta y cuatro", "Bélgica", 4),
				new Figurita(65, "sesenta y cinco", "Bélgica", 5),
				new Figurita(66, "sesenta y seis", "Bélgica", 6),
				new Figurita(67, "sesenta y siete", "Bélgica", 7),
				new Figurita(68, "sesenta y ocho", "Bélgica", 8),
				new Figurita(69, "sesenta y nueve", "Bélgica", 9),
				new Figurita(70, "setenta", "Bélgica", 10),
				new Figurita(71, "setenta y uno", "Bélgica", 11),
				new Figurita(72, "setenta y dos", "Bélgica", 12),
				new Figurita(73, "setenta y tres", "Camerún", 1),
				new Figurita(74, "setenta y cuatro", "Camerún", 2),
				new Figurita(75, "setenta y cinco", "Camerún", 3),
				new Figurita(76, "setenta y seis", "Camerún", 4),
				new Figurita(77, "setenta y siete", "Camerún", 5),
				new Figurita(78, "setenta y ocho", "Camerún", 6),
				new Figurita(79, "setenta y nueve", "Camerún", 7),
				new Figurita(80, "ochenta", "Camerún", 8),
				new Figurita(81, "ochenta y uno", "Camerún", 9),
				new Figurita(82, "ochenta y dos", "Camerún", 10),
				new Figurita(83, "ochenta y tres", "Camerún", 11),
				new Figurita(84, "ochenta y cuatro", "Camerún", 12),
				new Figurita(85, "ochenta y cinco", "Canadá", 1),
				new Figurita(86, "ochenta y seis", "Canadá", 2),
				new Figurita(87, "ochenta y siete", "Canadá", 3),
				new Figurita(88, "ochenta y ocho", "Canadá", 4),
				new Figurita(89, "ochenta y nueve", "Canadá", 5),
				new Figurita(90, "noventa", "Canadá", 6),
				new Figurita(91, "noventa y uno", "Canadá", 7),
				new Figurita(92, "noventa y dos", "Canadá", 8),
				new Figurita(93, "noventa y tres", "Canadá", 9),
				new Figurita(94, "noventa y cuatro", "Canadá", 10),
				new Figurita(95, "noventa y cinco", "Canadá", 11),
				new Figurita(96, "noventa y seis", "Canadá", 12),
				new Figurita(97, "noventa y siete", "Costa Rica", 1),
				new Figurita(98, "noventa y ocho", "Costa Rica", 2),
				new Figurita(99, "noventa y nueve", "Costa Rica", 3),
				new Figurita(100, "cien", "Costa Rica", 4),
				new Figurita(101, "cien 1", "Costa Rica", 5),
				new Figurita(102, "cien 2", "Costa Rica", 6),
				new Figurita(103, "cien 3", "Costa Rica", 7),
				new Figurita(104, "cien 4", "Costa Rica", 8),
				new Figurita(105, "cien 5", "Costa Rica", 9),
				new Figurita(106, "cien 6", "Costa Rica", 10),
				new Figurita(107, "cien 7", "Costa Rica", 11),
				new Figurita(108, "cien 8", "Costa Rica", 12),
				new Figurita(109, "cien 9", "Croacia", 1),
				new Figurita(110, "cien 10", "Croacia", 2),
				new Figurita(111, "cien 11", "Croacia", 3),
				new Figurita(112, "cien 12", "Croacia", 4),
				new Figurita(113, "cien 13", "Croacia", 5),
				new Figurita(114, "cien 14", "Croacia", 6),
				new Figurita(115, "cien 15", "Croacia", 7),
				new Figurita(116, "cien 16", "Croacia", 8),
				new Figurita(117, "cien 17", "Croacia", 9),
				new Figurita(118, "cien 18", "Croacia", 10),
				new Figurita(119, "cien 19", "Croacia", 11),
				new Figurita(120, "cien 20", "Croacia", 12),
				new Figurita(121, "cien 21", "Dinamarca", 1),
				new Figurita(122, "cien 22", "Dinamarca", 2),
				new Figurita(123, "cien 23", "Dinamarca", 3),
				new Figurita(124, "cien 24", "Dinamarca", 4),
				new Figurita(125, "cien 25", "Dinamarca", 5),
				new Figurita(126, "cien 26", "Dinamarca", 6),
				new Figurita(127, "cien 27", "Dinamarca", 7),
				new Figurita(128, "cien 28", "Dinamarca", 8),
				new Figurita(129, "cien 29", "Dinamarca", 9),
				new Figurita(130, "cien 30", "Dinamarca", 10),
				new Figurita(131, "cien 31", "Dinamarca", 11),
				new Figurita(132, "cien 32", "Dinamarca", 12),
				new Figurita(133, "cien 33", "Ecuador", 1),
				new Figurita(134, "cien 34", "Ecuador", 2),
				new Figurita(135, "cien 35", "Ecuador", 3),
				new Figurita(136, "cien 36", "Ecuador", 4),
				new Figurita(137, "cien 37", "Ecuador", 5),
				new Figurita(138, "cien 38", "Ecuador", 6),
				new Figurita(139, "cien 39", "Ecuador", 7),
				new Figurita(140, "cien 40", "Ecuador", 8),
				new Figurita(141, "cien 41", "Ecuador", 9),
				new Figurita(142, "cien 42", "Ecuador", 10),
				new Figurita(143, "cien 43", "Ecuador", 11),
				new Figurita(144, "cien 44", "Ecuador", 12),
				new Figurita(145, "cien 45", "España", 1),
				new Figurita(146, "cien 46", "España", 2),
				new Figurita(147, "cien 47", "España", 3),
				new Figurita(148, "cien 48", "España", 4),
				new Figurita(149, "cien 49", "España", 5),
				new Figurita(150, "cien 50", "España", 6),
				new Figurita(151, "cien 51", "España", 7),
				new Figurita(152, "cien 52", "España", 8),
				new Figurita(153, "cien 53", "España", 9),
				new Figurita(154, "cien 54", "España", 10),
				new Figurita(155, "cien 55", "España", 11),
				new Figurita(156, "cien 56", "España", 12),
				new Figurita(157, "cien 57", "Estados Unidos", 1),
				new Figurita(158, "cien 58", "Estados Unidos", 2),
				new Figurita(159, "cien 59", "Estados Unidos", 3),
				new Figurita(160, "cien 60", "Estados Unidos", 4),
				new Figurita(161, "cien 61", "Estados Unidos", 5),
				new Figurita(162, "cien 62", "Estados Unidos", 6),
				new Figurita(163, "cien 63", "Estados Unidos", 7),
				new Figurita(164, "cien 64", "Estados Unidos", 8),
				new Figurita(165, "cien 65", "Estados Unidos", 9),
				new Figurita(166, "cien 66", "Estados Unidos", 10),
				new Figurita(167, "cien 67", "Estados Unidos", 11),
				new Figurita(168, "cien 68", "Estados Unidos", 12),
				new Figurita(169, "cien 69", "Francia", 1),
				new Figurita(170, "cien 70", "Francia", 2),
				new Figurita(171, "cien 71", "Francia", 3),
				new Figurita(172, "cien 72", "Francia", 4),
				new Figurita(173, "cien 73", "Francia", 5),
				new Figurita(174, "cien 74", "Francia", 6),
				new Figurita(175, "cien 75", "Francia", 7),
				new Figurita(176, "cien 76", "Francia", 8),
				new Figurita(177, "cien 77", "Francia", 9),
				new Figurita(178, "cien 78", "Francia", 10),
				new Figurita(179, "cien 79", "Francia", 11),
				new Figurita(180, "cien 80", "Francia", 12),
				new Figurita(181, "cien 81", "Gales", 1),
				new Figurita(182, "cien 82", "Gales", 2),
				new Figurita(183, "cien 83", "Gales", 3),
				new Figurita(184, "cien 84", "Gales", 4),
				new Figurita(185, "cien 85", "Gales", 5),
				new Figurita(186, "cien 86", "Gales", 6),
				new Figurita(187, "cien 87", "Gales", 7),
				new Figurita(188, "cien 88", "Gales", 8),
				new Figurita(189, "cien 89", "Gales", 9),
				new Figurita(190, "cien 90", "Gales", 10),
				new Figurita(191, "cien 91", "Gales", 11),
				new Figurita(192, "cien 92", "Gales", 12),
				new Figurita(193, "cien 93", "Ghana", 1),
				new Figurita(194, "cien 94", "Ghana", 2),
				new Figurita(195, "cien 95", "Ghana", 3),
				new Figurita(196, "cien 96", "Ghana", 4),
				new Figurita(197, "cien 97", "Ghana", 5),
				new Figurita(198, "cien 98", "Ghana", 6),
				new Figurita(199, "cien 99", "Ghana", 7),
				new Figurita(200, "doscientos", "Ghana", 8),
				new Figurita(201, "doscientos 1", "Ghana", 9),
				new Figurita(202, "doscientos 2", "Ghana", 10),
				new Figurita(203, "doscientos 3", "Ghana", 11),
				new Figurita(204, "doscientos 4", "Ghana", 12),
				new Figurita(205, "doscientos 5", "Inglaterra", 1),
				new Figurita(206, "doscientos 6", "Inglaterra", 2),
				new Figurita(207, "doscientos 7", "Inglaterra", 3),
				new Figurita(208, "doscientos 8", "Inglaterra", 4),
				new Figurita(209, "doscientos 9", "Inglaterra", 5),
				new Figurita(210, "doscientos 10", "Inglaterra", 6),
				new Figurita(211, "doscientos 11", "Inglaterra", 7),
				new Figurita(212, "doscientos 12", "Inglaterra", 8),
				new Figurita(213, "doscientos 13", "Inglaterra", 9),
				new Figurita(214, "doscientos 14", "Inglaterra", 10),
				new Figurita(215, "doscientos 15", "Inglaterra", 11),
				new Figurita(216, "doscientos 16", "Inglaterra", 12),
				new Figurita(217, "doscientos 17", "Irán", 1),
				new Figurita(218, "doscientos 18", "Irán", 2),
				new Figurita(219, "doscientos 19", "Irán", 3),
				new Figurita(220, "doscientos 20", "Irán", 4),
				new Figurita(221, "doscientos 21", "Irán", 5),
				new Figurita(222, "doscientos 22", "Irán", 6),
				new Figurita(223, "doscientos 23", "Irán", 7),
				new Figurita(224, "doscientos 24", "Irán", 8),
				new Figurita(225, "doscientos 25", "Irán", 9),
				new Figurita(226, "doscientos 26", "Irán", 10),
				new Figurita(227, "doscientos 27", "Irán", 11),
				new Figurita(228, "doscientos 28", "Irán", 12),
				new Figurita(229, "doscientos 29", "Japón", 1),
				new Figurita(230, "doscientos 30", "Japón", 2),
				new Figurita(231, "doscientos 31", "Japón", 3),
				new Figurita(232, "doscientos 32", "Japón", 4),
				new Figurita(233, "doscientos 33", "Japón", 5),
				new Figurita(234, "doscientos 34", "Japón", 6),
				new Figurita(235, "doscientos 35", "Japón", 7),
				new Figurita(236, "doscientos 36", "Japón", 8),
				new Figurita(237, "doscientos 37", "Japón", 9),
				new Figurita(238, "doscientos 38", "Japón", 10),
				new Figurita(239, "doscientos 39", "Japón", 11),
				new Figurita(240, "doscientos 40", "Japón", 12),
				new Figurita(241, "doscientos 41", "Marruecos", 1),
				new Figurita(242, "doscientos 42", "Marruecos", 2),
				new Figurita(243, "doscientos 43", "Marruecos", 3),
				new Figurita(244, "doscientos 44", "Marruecos", 4),
				new Figurita(245, "doscientos 45", "Marruecos", 5),
				new Figurita(246, "doscientos 46", "Marruecos", 6),
				new Figurita(247, "doscientos 47", "Marruecos", 7),
				new Figurita(248, "doscientos 48", "Marruecos", 8),
				new Figurita(249, "doscientos 49", "Marruecos", 9),
				new Figurita(250, "doscientos 50", "Marruecos", 10),
				new Figurita(251, "doscientos 51", "Marruecos", 11),
				new Figurita(252, "doscientos 52", "Marruecos", 12),
				new Figurita(253, "doscientos 53", "México", 1),
				new Figurita(254, "doscientos 54", "México", 2),
				new Figurita(255, "doscientos 55", "México", 3),
				new Figurita(256, "doscientos 56", "México", 4),
				new Figurita(257, "doscientos 57", "México", 5),
				new Figurita(258, "doscientos 58", "México", 6),
				new Figurita(259, "doscientos 59", "México", 7),
				new Figurita(260, "doscientos 60", "México", 8),
				new Figurita(261, "doscientos 61", "México", 9),
				new Figurita(262, "doscientos 62", "México", 10),
				new Figurita(263, "doscientos 63", "México", 11),
				new Figurita(264, "doscientos 64", "México", 12),
				new Figurita(265, "doscientos 65", "Países Bajos", 1),
				new Figurita(266, "doscientos 66", "Países Bajos", 2),
				new Figurita(267, "doscientos 67", "Países Bajos", 3),
				new Figurita(268, "doscientos 68", "Países Bajos", 4),
				new Figurita(269, "doscientos 69", "Países Bajos", 5),
				new Figurita(270, "doscientos 70", "Países Bajos", 6),
				new Figurita(271, "doscientos 71", "Países Bajos", 7),
				new Figurita(272, "doscientos 72", "Países Bajos", 8),
				new Figurita(273, "doscientos 73", "Países Bajos", 9),
				new Figurita(274, "doscientos 74", "Países Bajos", 10),
				new Figurita(275, "doscientos 75", "Países Bajos", 11),
				new Figurita(276, "doscientos 76", "Países Bajos", 12),
				new Figurita(277, "doscientos 77", "Polonia", 1),
				new Figurita(278, "doscientos 78", "Polonia", 2),
				new Figurita(279, "doscientos 79", "Polonia", 3),
				new Figurita(280, "doscientos 80", "Polonia", 4),
				new Figurita(281, "doscientos 81", "Polonia", 5),
				new Figurita(282, "doscientos 82", "Polonia", 6),
				new Figurita(283, "doscientos 83", "Polonia", 7),
				new Figurita(284, "doscientos 84", "Polonia", 8),
				new Figurita(285, "doscientos 85", "Polonia", 9),
				new Figurita(286, "doscientos 86", "Polonia", 10),
				new Figurita(287, "doscientos 87", "Polonia", 11),
				new Figurita(288, "doscientos 88", "Polonia", 12),
				new Figurita(289, "doscientos 89", "Portugal", 1),
				new Figurita(290, "doscientos 90", "Portugal", 2),
				new Figurita(291, "doscientos 91", "Portugal", 3),
				new Figurita(292, "doscientos 92", "Portugal", 4),
				new Figurita(293, "doscientos 93", "Portugal", 5),
				new Figurita(294, "doscientos 94", "Portugal", 6),
				new Figurita(295, "doscientos 95", "Portugal", 7),
				new Figurita(296, "doscientos 96", "Portugal", 8),
				new Figurita(297, "doscientos 97", "Portugal", 9),
				new Figurita(298, "doscientos 98", "Portugal", 10),
				new Figurita(299, "doscientos 99", "Portugal", 11),
				new Figurita(300, "trescientos", "Portugal", 12),
				new Figurita(301, "trescientos 1", "Qatar", 1),
				new Figurita(302, "trescientos 2", "Qatar", 2),
				new Figurita(303, "trescientos 3", "Qatar", 3),
				new Figurita(304, "trescientos 4", "Qatar", 4),
				new Figurita(305, "trescientos 5", "Qatar", 5),
				new Figurita(306, "trescientos 6", "Qatar", 6),
				new Figurita(307, "trescientos 7", "Qatar", 7),
				new Figurita(308, "trescientos 8", "Qatar", 8),
				new Figurita(309, "trescientos 9", "Qatar", 9),
				new Figurita(310, "trescientos 10", "Qatar", 10),
				new Figurita(311, "trescientos 11", "Qatar", 11),
				new Figurita(312, "trescientos 12", "Qatar", 12),
				new Figurita(313, "trescientos 13", "República de Corea", 1),
				new Figurita(314, "trescientos 14", "República de Corea", 2),
				new Figurita(315, "trescientos 15", "República de Corea", 3),
				new Figurita(316, "trescientos 16", "República de Corea", 4),
				new Figurita(317, "trescientos 17", "República de Corea", 5),
				new Figurita(318, "trescientos 18", "República de Corea", 6),
				new Figurita(319, "trescientos 19", "República de Corea", 7),
				new Figurita(320, "trescientos 20", "República de Corea", 8),
				new Figurita(321, "trescientos 21", "República de Corea", 9),
				new Figurita(322, "trescientos 22", "República de Corea", 10),
				new Figurita(323, "trescientos 23", "República de Corea", 11),
				new Figurita(324, "trescientos 24", "República de Corea", 12),
				new Figurita(325, "trescientos 25", "Senegal", 1),
				new Figurita(326, "trescientos 26", "Senegal", 2),
				new Figurita(327, "trescientos 27", "Senegal", 3),
				new Figurita(328, "trescientos 28", "Senegal", 4),
				new Figurita(329, "trescientos 29", "Senegal", 5),
				new Figurita(330, "trescientos 30", "Senegal", 6),
				new Figurita(331, "trescientos 31", "Senegal", 7),
				new Figurita(332, "trescientos 32", "Senegal", 8),
				new Figurita(333, "trescientos 33", "Senegal", 9),
				new Figurita(334, "trescientos 34", "Senegal", 10),
				new Figurita(335, "trescientos 35", "Senegal", 11),
				new Figurita(336, "trescientos 36", "Senegal", 12),
				new Figurita(337, "trescientos 37", "Serbia", 1),
				new Figurita(338, "trescientos 38", "Serbia", 2),
				new Figurita(339, "trescientos 39", "Serbia", 3),
				new Figurita(340, "trescientos 40", "Serbia", 4),
				new Figurita(341, "trescientos 41", "Serbia", 5),
				new Figurita(342, "trescientos 42", "Serbia", 6),
				new Figurita(343, "trescientos 43", "Serbia", 7),
				new Figurita(344, "trescientos 44", "Serbia", 8),
				new Figurita(345, "trescientos 45", "Serbia", 9),
				new Figurita(346, "trescientos 46", "Serbia", 10),
				new Figurita(347, "trescientos 47", "Serbia", 11),
				new Figurita(348, "trescientos 48", "Serbia", 12),
				new Figurita(349, "trescientos 49", "Suiza", 1),
				new Figurita(350, "trescientos 50", "Suiza", 2),
				new Figurita(351, "trescientos 51", "Suiza", 3),
				new Figurita(352, "trescientos 52", "Suiza", 4),
				new Figurita(353, "trescientos 53", "Suiza", 5),
				new Figurita(354, "trescientos 54", "Suiza", 6),
				new Figurita(355, "trescientos 55", "Suiza", 7),
				new Figurita(356, "trescientos 56", "Suiza", 8),
				new Figurita(357, "trescientos 57", "Suiza", 9),
				new Figurita(358, "trescientos 58", "Suiza", 10),
				new Figurita(359, "trescientos 59", "Suiza", 11),
				new Figurita(360, "trescientos 60", "Suiza", 12),
				new Figurita(361, "trescientos 61", "Túnez", 1),
				new Figurita(362, "trescientos 62", "Túnez", 2),
				new Figurita(363, "trescientos 63", "Túnez", 3),
				new Figurita(364, "trescientos 64", "Túnez", 4),
				new Figurita(365, "trescientos 65", "Túnez", 5),
				new Figurita(366, "trescientos 66", "Túnez", 6),
				new Figurita(367, "trescientos 67", "Túnez", 7),
				new Figurita(368, "trescientos 68", "Túnez", 8),
				new Figurita(369, "trescientos 69", "Túnez", 9),
				new Figurita(370, "trescientos 70", "Túnez", 10),
				new Figurita(371, "trescientos 71", "Túnez", 11),
				new Figurita(372, "trescientos 72", "Túnez", 12),
				new Figurita(373, "trescientos 73", "Uruguay", 1),
				new Figurita(374, "trescientos 74", "Uruguay", 2),
				new Figurita(375, "trescientos 75", "Uruguay", 3),
				new Figurita(376, "trescientos 76", "Uruguay", 4),
				new Figurita(377, "trescientos 77", "Uruguay", 5),
				new Figurita(378, "trescientos 78", "Uruguay", 6),
				new Figurita(279, "trescientos 79", "Uruguay", 7),
				new Figurita(380, "trescientos 80", "Uruguay", 8),
				new Figurita(381, "trescientos 81", "Uruguay", 9),
				new Figurita(382, "trescientos 82", "Uruguay", 10),
				new Figurita(383, "trescientos 83", "Uruguay", 11),
				new Figurita(384, "trescientos 84", "Uruguay", 12),
		};
		for (int i = 0; i < figus.length; i++) {
			figus[i].generarValor(calcularValorBase(figus[i].getPais(), figus[i].getNumero()));
		}
		return figus;
	}

	private FiguTOP10[] generarFigusTOP10() {
		FiguTOP10[] figus = new FiguTOP10[] {
				new FiguTOP10(385, "Paolo Rossi", "Italia", 20),
				new FiguTOP10(386, "Zico", "Brasil", 10),
				new FiguTOP10(387, "Diego Maradona", "Argentina", 10),
				new FiguTOP10(388, "Rudi Völler", "Alemania", 9),
				new FiguTOP10(389, "Salvatore Schillaci", "Italia", 19),
				new FiguTOP10(390, "Lothar Matthäus", "Alemania", 10),
				new FiguTOP10(391, "Romario", "Brasil", 11),
				new FiguTOP10(392, "Roberto Baggio", "Italia", 10),
				new FiguTOP10(393, "Ronaldo", "Brasil", 9),
				new FiguTOP10(394, "Davor Šuker", "Croacia", 9),
				new FiguTOP10(395, "Miroslav Klose", "Alemania", 11),
				new FiguTOP10(396, "Ronaldo", "Brasil", 9),
				new FiguTOP10(397, "Thierry Henry", "Francia", 12),
				new FiguTOP10(398, "Marco Materazzi", "Italia", 23),
				new FiguTOP10(399, "Diego Forlán", "Uruguay", 10),
				new FiguTOP10(400, "Wesley Sneijder", "Países Bajos", 10),
				new FiguTOP10(401, "Lionel Messi", "Argentina", 10),
				new FiguTOP10(402, "Thomas Müller", "Alemania", 13),
				new FiguTOP10(403, "Mario Mandžukić", "Croacia", 17),
				new FiguTOP10(405, "Romelu Lukaku", "Bélgica", 9),
		};
		mundialTOP10(figus);
		balonTOP10(figus);
		for (int i = 0; i < figus.length; i++) {
			figus[i].generarValor(calcularValorBase(figus[i].getPais(), figus[i].getNumero()));
			figus[i].generarValorFinal(figus[i].getValor(), figus[i].getBalon());
		}
		return figus;
	}

	/**
	 * Se utiliza para settear en que mundial cada jugador de la coleccion de figuritas TOP 10
	 * recibio el balon de oro o de plata.
	 * @param coleccion
	 */
	private void mundialTOP10(FiguTOP10[] coleccion) {
		int i = 0;
		for (String pais : listadoDeMundialesTop10) {
			int j = 0;
			while (j < 2) {
				coleccion[i].setMundial(pais);
				j++;
				i++;
			}
		}
	}

	private void balonTOP10(FiguTOP10[] coleccion) {
		for (int i = 0; i <= coleccion.length - 1; i++) {
			if (i % 2 == 0) {
				coleccion[i].setBalon("Oro");
			} else {
				coleccion[i].setBalon("Plata");
			}
		}
	}

	private String[] generarCodigos() {
		return new String[] {
				"DGvdRswb", "S45QWSLv", "NpCH46mQ", "RQLkSwH4",
				"Xm8CvsC4", "FwZckZRn", "4PALTWRz", "72r4xXV8",
				"DRTte5CZ", "XFsCp2uG", "BHc5fnR9", "mJjKGxUz",
				"pBUeDw5n"
		};
	}

	public String generarSorteo() {
		int pos = random.nextInt(premiosInstantaneos.length);
		return premiosInstantaneos[pos];
	}

	/**
	 * Crea un nuevo sobre e itera dentro de un for con el tamano que tendra el
	 * sobre, cada vez que itera genera un numero
	 * aleatorio entre el tamano de todas la figuritas posibles y 0. Luego, ese
	 * numero se utiliza para anadir una figurita
	 * que este en esa posicion de toda la coleccion de figuritas.
	 */
	List<Figurita> generarSobre(int cantFigus) {
		if (cantFigus != 4) {
			throw new RuntimeException("El sobre no puede contener menos de 4 figuritas");
		}
		ArrayList<Figurita> sobre = new ArrayList<Figurita>();
		for (int i = 0; i <= cantFigus - 1; i++) {
			int pos = random.nextInt(aColeccionar.length);
			sobre.add(aColeccionar[pos]);
		}
		return sobre;
	}

	List<Figurita> generarSobrePromo(int cantFigus, String codigo) {
		if (cantFigus != 4) {
			throw new RuntimeException("El sobre no puede contener menos de 4 figuritas");
		}
		for (String elem : codigosPromocionales) {
			for (String usado : codigosUsados) {
				if (usado.equals(elem)) {
					throw new RuntimeException("El codigo ya fue utilizado");
				}
			}
			if (codigo.equals(elem)) {
				codigosUsados.add(elem); // cuando el codigo es utilizado lo agrega a una ArrayList de codigos
											// utilizados
				return generarSobre(cantFigus);
			}
		}
		throw new RuntimeException("Codigo invalido");
	}

	List<FiguTOP10> generarSobreTOP10(int cantFigus) {
		if (cantFigus != 4) {
			throw new RuntimeException("El sobre no puede contener menos de 4 figuritas TOP10");
		}
		ArrayList<FiguTOP10> sobreTop10 = new ArrayList<>();
		for (int i = 0; i < cantFigus - 1; i++) {
			int pos = random.nextInt(coleccionTOP10.length);
			sobreTop10.add(coleccionTOP10[pos]);
		}
		return sobreTop10;
	}
}