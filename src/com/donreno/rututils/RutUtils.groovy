package com.donreno.rututils


class RutUtils {
	
	public static boolean validateRut(String rut){
		def digito = rut.split("-")[1]
		def expectedDigito = getDigitoVerificadorRut(rut)
		
		return digito == expectedDigito
	}
	
	public static String getDigitoVerificadorRut(String rut){
		def rutWithoutDigit = extractRutWithoutDigit(rut)
		
		def reversedRutDigits = rutToCleanReversedArray(rutWithoutDigit)
		
		def sum = multiplySerieOnDigitsAndSum(reversedRutDigits)
		
		def digito = calculateDigitOnSum(sum)
		
		return digito
	}

	private static String extractRutWithoutDigit(String rut) {
		return rut.split("-").length > 1 ? rut.split("-")[0]:rut
	}

	private static String calculateDigitOnSum(int sum) {
		def digito = 11-(sum%11)
		
		return translateDigit(digito)
	}

	private static String translateDigit(digito) {
		switch (digito){
			case 1..9:
				digito = "${digito}"
				break
			case 10:
				digito = "K"
				break
			case 11:
				digito = "0"
				break
		}
		return digito
	}

	private static multiplySerieOnDigitsAndSum(reversedRutDigits) {
		def sum = 0

		CircularCollectionIterator toMultiplySerie = new CircularCollectionIterator(2..7)

		reversedRutDigits.each(){ val ->
			sum = sumMultipliedSerieValue(val, toMultiplySerie.current(), sum)

			toMultiplySerie.next()
		}
		return sum
	}

	private static sumMultipliedSerieValue(val, currentMultiplier, sum) {
		def currentValue = Integer.valueOf(val)
		def multiplicatedValue = currentValue*currentMultiplier
		return sum + multiplicatedValue
	}

	private static rutToCleanReversedArray(String rut) {
		return cleanRut(rut).reverse().toList()
	}

	private static String cleanRut(String rut) {
		return rut.replaceAll("\\.","")
	}

}
