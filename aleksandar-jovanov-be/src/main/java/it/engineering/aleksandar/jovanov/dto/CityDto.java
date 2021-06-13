package it.engineering.aleksandar.jovanov.dto;





public class CityDto implements  MyDto {
	
	
		private Long cityCode;
		

		private String cityName;

		public CityDto() {
		}

		public CityDto(Long cityCode, String cityName) {
			super();
			this.cityCode = cityCode;
			this.cityName = cityName;
		}
	//GET SET HASHCODE EQUALS TOSTIRNG metode

		public Long getCityCode() {
			return cityCode;
		}

		public void setCityCode(Long cityCode) {
			this.cityCode = cityCode;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		@Override
		public String toString() {
			return "CityDto [cityCode=" + cityCode + ", cityName=" + cityName + "]";
		}
	
}
