package come.SocatraAirline.SocatraAirline.enums;

import lombok.Getter;

@Getter
public enum City {
    // Yemen
    ADEN(Country.YEMEN),
    SANAA(Country.YEMEN),
    HADRAMOUT(Country.YEMEN),
    SOCOTRA(Country.YEMEN),
    TAIZ(Country.YEMEN),
    AL_HUDAYDAH(Country.YEMEN),

    // Saudi Arabia
    RIYADH(Country.SAUDI_ARABIA),
    JEDDAH(Country.SAUDI_ARABIA),
    MECCA(Country.SAUDI_ARABIA),
    MEDINA(Country.SAUDI_ARABIA),
    DAMMAM(Country.SAUDI_ARABIA),

    // UAE
    DUBAI(Country.UNITED_ARAB_EMIRATES),
    ABU_DHABI(Country.UNITED_ARAB_EMIRATES),
    SHARJAH(Country.UNITED_ARAB_EMIRATES),
    AJMAN(Country.UNITED_ARAB_EMIRATES),

    // Oman
    MUSCAT(Country.OMAN),
    SALALAH(Country.OMAN),
    SOHAR(Country.OMAN),

    // India
    DELHI(Country.INDIA),
    MUMBAI(Country.INDIA),
    BANGALORE(Country.INDIA),
    HYDERABAD(Country.INDIA),

    // Russia
    MOSCOW(Country.RUSSIA),
    SAINT_PETERSBURG(Country.RUSSIA),
    KAZAN(Country.RUSSIA),

    // Egypt
    CAIRO(Country.EGYPT),
    ALEXANDRIA(Country.EGYPT),
    LUXOR(Country.EGYPT),

    // Qatar
    DOHA(Country.QATAR),

    // Jordan
    AMMAN(Country.JORDAN),
    AQABA(Country.JORDAN),

    // Turkey
    ISTANBUL(Country.TURKEY),
    ANKARA(Country.TURKEY),
    IZMIR(Country.TURKEY),

    // Germany
    BERLIN(Country.GERMANY),
    MUNICH(Country.GERMANY),
    FRANKFURT(Country.GERMANY),

    // France
    PARIS(Country.FRANCE),
    MARSEILLE(Country.FRANCE),
    LYON(Country.FRANCE),

    // UK
    LONDON(Country.UNITED_KINGDOM),
    MANCHESTER(Country.UNITED_KINGDOM),
    EDINBURGH(Country.UNITED_KINGDOM),

    // USA
    NEW_YORK(Country.UNITED_STATES),
    LOS_ANGELES(Country.UNITED_STATES),
    CHICAGO(Country.UNITED_STATES),

    // China
    BEIJING(Country.CHINA),
    SHANGHAI(Country.CHINA),
    GUANGZHOU(Country.CHINA),

    // Japan
    TOKYO(Country.JAPAN),
    OSAKA(Country.JAPAN),
    KYOTO(Country.JAPAN);

    private final Country country;

    City(Country country) {
        this.country = country;
    }

}
