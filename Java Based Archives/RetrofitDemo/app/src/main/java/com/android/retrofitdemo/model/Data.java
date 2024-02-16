// Data.java


package com.android.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Data {
    @SerializedName("capital")
    private List<String> capital;

    @SerializedName("flag")
    private String flag;

    @SerializedName("independent")
    private boolean independent;

    @SerializedName("landlocked")
    private boolean landlocked;

    @SerializedName("borders")
    private List<String> borders;

    @SerializedName("postalCode")
    private PostalCode postalCode;

    @SerializedName("flags")
    private Flags flags;

    @SerializedName("capitalInfo")
    private CapitalInfo capitalInfo;

    @SerializedName("ccn3")
    private String ccn3;

    @SerializedName("coatOfArms")
    private CoatOfArms coatOfArms;

    @SerializedName("demonyms")
    private Demonyms demonyms;

    @SerializedName("fifa")
    private String fifa;

    @SerializedName("cioc")
    private String cioc;

    @SerializedName("car")
    private Car car;

    @SerializedName("translations")
    private Map<String, Translation> translations;

    @SerializedName("altSpellings")
    private List<String> altSpellings;

    @SerializedName("area")
    private double area;

    @SerializedName("languages")
    private Languages languages;

    @SerializedName("maps")
    private Maps maps;

    @SerializedName("subregion")
    private String subregion;

    @SerializedName("idd")
    private Idd idd;

    @SerializedName("tld")
    private List<String> tld;

    @SerializedName("unMember")
    private boolean unMember;

    @SerializedName("gini")
    private Gini gini;

    @SerializedName("continents")
    private List<String> continents;

    @SerializedName("population")
    private long population;

    @SerializedName("startOfWeek")
    private String startOfWeek;

    @SerializedName("timezones")
    private List<String> timezones;

    @SerializedName("name")
    private Name name;

    @SerializedName("cca3")
    private String cca3;

    @SerializedName("region")
    private String region;

    @SerializedName("latlng")
    private List<Double> latlng;

    @SerializedName("cca2")
    private String cca2;

    @SerializedName("status")
    private String status;

    @SerializedName("currencies")
    private Currencies currencies;


    public List<String> getCapital() { return capital; }
    public void setCapital(List<String> value) { this.capital = value; }

    public String getFlag() { return flag; }
    public void setFlag(String value) { this.flag = value; }

    public boolean getIndependent() { return independent; }
    public void setIndependent(boolean value) { this.independent = value; }

    public boolean getLandlocked() { return landlocked; }
    public void setLandlocked(boolean value) { this.landlocked = value; }

    public List<String> getBorders() { return borders; }
    public void setBorders(List<String> value) { this.borders = value; }

    public PostalCode getPostalCode() { return postalCode; }
    public void setPostalCode(PostalCode value) { this.postalCode = value; }

    public Flags getFlags() { return flags; }
    public void setFlags(Flags value) { this.flags = value; }

    public CapitalInfo getCapitalInfo() { return capitalInfo; }
    public void setCapitalInfo(CapitalInfo value) { this.capitalInfo = value; }

    public String getCcn3() { return ccn3; }
    public void setCcn3(String value) { this.ccn3 = value; }

    public CoatOfArms getCoatOfArms() { return coatOfArms; }
    public void setCoatOfArms(CoatOfArms value) { this.coatOfArms = value; }

    public Demonyms getDemonyms() { return demonyms; }
    public void setDemonyms(Demonyms value) { this.demonyms = value; }

    public String getFifa() { return fifa; }
    public void setFifa(String value) { this.fifa = value; }

    public String getCioc() { return cioc; }
    public void setCioc(String value) { this.cioc = value; }

    public Car getCar() { return car; }
    public void setCar(Car value) { this.car = value; }

    public Map<String, Translation> getTranslations() { return translations; }
    public void setTranslations(Map<String, Translation> value) { this.translations = value; }

    public List<String> getAltSpellings() { return altSpellings; }
    public void setAltSpellings(List<String> value) { this.altSpellings = value; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public Languages getLanguages() { return languages; }
    public void setLanguages(Languages value) { this.languages = value; }

    public Maps getMaps() { return maps; }
    public void setMaps(Maps value) { this.maps = value; }

    public String getSubregion() { return subregion; }
    public void setSubregion(String value) { this.subregion = value; }

    public Idd getIdd() { return idd; }
    public void setIdd(Idd value) { this.idd = value; }

    public List<String> gettld() { return tld; }
    public void settld(List<String> value) { this.tld = value; }

    public boolean getUnMember() { return unMember; }
    public void setUnMember(boolean value) { this.unMember = value; }

    public Gini getGini() { return gini; }
    public void setGini(Gini value) { this.gini = value; }

    public List<String> getContinents() { return continents; }
    public void setContinents(List<String> value) { this.continents = value; }

    public long getPopulation() { return population; }
    public void setPopulation(long value) { this.population = value; }

    public String getStartOfWeek() { return startOfWeek; }
    public void setStartOfWeek(String value) { this.startOfWeek = value; }

    public List<String> getTimezones() { return timezones; }
    public void setTimezones(List<String> value) { this.timezones = value; }

    public Name getName() { return name; }
    public void setName(Name value) { this.name = value; }

    public String getCca3() { return cca3; }
    public void setCca3(String value) { this.cca3 = value; }

    public String getRegion() { return region; }
    public void setRegion(String value) { this.region = value; }

    public List<Double> getLatlng() { return latlng; }
    public void setLatlng(List<Double> value) { this.latlng = value; }

    public String getCca2() { return cca2; }
    public void setCca2(String value) { this.cca2 = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public Currencies getCurrencies() { return currencies; }
    public void setCurrencies(Currencies value) { this.currencies = value; }

}

// CapitalInfo.java
class CapitalInfo {
    @SerializedName("latlng")
    private List<Double> latlng;

    public List<Double> getLatlng() { return latlng; }
    public void setLatlng(List<Double> value) { this.latlng = value; }
}

// Car.java
class Car {
    @SerializedName("side")
    private String side;
    @SerializedName("signs")
    private List<String> signs;

    public String getSide() { return side; }
    public void setSide(String value) { this.side = value; }

    public List<String> getSigns() { return signs; }
    public void setSigns(List<String> value) { this.signs = value; }
}

// CoatOfArms.java
class CoatOfArms {
    @SerializedName("svg")
    private String svg;
    @SerializedName("png")
    private String png;

    public String getsvg() { return svg; }
    public void setsvg(String value) { this.svg = value; }

    public String getpng() { return png; }
    public void setpng(String value) { this.png = value; }
}

// Currencies.java
class Currencies {
    @SerializedName("eur")
    private Eur eur;

    public Eur getEur() { return eur; }
    public void setEur(Eur value) { this.eur = value; }
}

// Eur.java
class Eur {

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("name")
    private String name;

    public String getSymbol() { return symbol; }
    public void setSymbol(String value) { this.symbol = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
}

// Demonyms.java
class Demonyms {
    @SerializedName("fra")
    private Eng fra;
    @SerializedName("eng")
    private Eng eng;

    public Eng getFra() { return fra; }
    public void setFra(Eng value) { this.fra = value; }

    public Eng getEng() { return eng; }
    public void setEng(Eng value) { this.eng = value; }
}

// Eng.java
class Eng {
    @SerializedName("f")
    private String f;
    @SerializedName("m")
    private String m;

    public String getF() { return f; }
    public void setF(String value) { this.f = value; }

    public String getM() { return m; }
    public void setM(String value) { this.m = value; }
}

// Flags.java
class Flags {
    @SerializedName("svg")
    private String svg;
    @SerializedName("png")
    private String png;

    @SerializedName("alt")
    private String alt;

    public String getsvg() { return svg; }
    public void setsvg(String value) { this.svg = value; }

    public String getpng() { return png; }
    public void setpng(String value) { this.png = value; }

    public String getAlt() { return alt; }
    public void setAlt(String value) { this.alt = value; }
}

// Gini.java
class Gini {
    @SerializedName("the2016")
    private double the2016;

    public double getThe2016() { return the2016; }
    public void setThe2016(double value) { this.the2016 = value; }
}

// Idd.java
class Idd {
    @SerializedName("suffixes")
    private List<String> suffixes;
    @SerializedName("root")
    private String root;

    public List<String> getSuffixes() { return suffixes; }
    public void setSuffixes(List<String> value) { this.suffixes = value; }

    public String getRoot() { return root; }
    public void setRoot(String value) { this.root = value; }
}

// Languages.java
class Languages {

    @SerializedName("deu")
    private String deu;

    public String getDeu() { return deu; }
    public void setDeu(String value) { this.deu = value; }
}

// Maps.java
class Maps {
    @SerializedName("openStreetMaps")
    private String openStreetMaps;
    @SerializedName("googleMaps")
    private String googleMaps;

    public String getOpenStreetMaps() { return openStreetMaps; }
    public void setOpenStreetMaps(String value) { this.openStreetMaps = value; }

    public String getGoogleMaps() { return googleMaps; }
    public void setGoogleMaps(String value) { this.googleMaps = value; }
}

// Name.java
class Name {
    @SerializedName("nativeName")
    private NativeName nativeName;
    @SerializedName("common")
    private String common;
    @SerializedName("official")
    private String official;

    public NativeName getNativeName() { return nativeName; }
    public void setNativeName(NativeName value) { this.nativeName = value; }

    public String getCommon() { return common; }
    public void setCommon(String value) { this.common = value; }

    public String getOfficial() { return official; }
    public void setOfficial(String value) { this.official = value; }
    @Override
    public String toString() {
        return this.common;
    }
}

// NativeName.java
class NativeName {
    @SerializedName("deu")
    private Translation deu;

    public Translation getDeu() { return deu; }
    public void setDeu(Translation value) { this.deu = value; }
}

// Translation.java
class Translation {
    @SerializedName("common")
    private String common;
    @SerializedName("official")
    private String official;

    public String getCommon() { return common; }
    public void setCommon(String value) { this.common = value; }

    public String getOfficial() { return official; }
    public void setOfficial(String value) { this.official = value; }
}

// PostalCode.java
class PostalCode {
    @SerializedName("regex")
    private String regex;
    @SerializedName("format")
    private String format;

    public String getRegex() { return regex; }
    public void setRegex(String value) { this.regex = value; }

    public String getFormat() { return format; }
    public void setFormat(String value) { this.format = value; }



}
