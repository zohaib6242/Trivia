package com.zohaib.trivia.constants;

import com.google.gson.annotations.SerializedName;

public enum Category {

    @SerializedName("Any")
    Any("Any"),

    @SerializedName("General Knowledge")
    General_Knowledge("General Knowledge"),

    @SerializedName("Entertainment: Books")
    Entertainment_Books("Entertainment: Books"),

    @SerializedName("Entertainment: Film")
    Entertainment_Film("Entertainment: Film"),

    @SerializedName("Entertainment: Music")
    Entertainment_Music("Entertainment: Music"),

    @SerializedName("Entertainment: Musicals & Theatres")
    Entertainment_Musicals_Theatres("Entertainment: Musicals & Theatres"),

    @SerializedName("Entertainment: Television")
    Entertainment_Television("Entertainment: Television"),

    @SerializedName("Entertainment: Video Games")
    Entertainment_Video_Games("Entertainment: Video Games"),

    @SerializedName("Entertainment: Board Games")
    Entertainment_Board_Games("Entertainment: Board Games"),

    @SerializedName("Science & Nature")
    Science_Nature("Science & Nature"),

    @SerializedName("Science: Computers")
    Science_Computers("Science: Computers"),

    @SerializedName("Science: Mathematics")
    Science_Mathematics("Science: Mathematics"),

    @SerializedName("Mythology")
    Mythology("Mythology"),

    @SerializedName("Sports")
    Sports("Sports"),

    @SerializedName("Geography")
    Geography("Geography"),

    @SerializedName("History")
    History("History"),

    @SerializedName("Politics")
    Politics("Politics"),

    @SerializedName("Art")
    Art("Art"),

    @SerializedName("Celebrities")
    Celebrities("Celebrities"),

    @SerializedName("Animals")
    Animals("Animals"),

    @SerializedName("Vehicles")
    Vehicles("Vehicles"),

    @SerializedName("Entertainment: Comics")
    Entertainment_Comics("Entertainment: Comics"),

    @SerializedName("Science: Gadgets")
    Science_Gadgets("Science: Gadgets"),

    @SerializedName("Entertainment: Japanese Anime & Manga")
    Entertainment_Japanese_Anime_Manga("Entertainment: Japanese Anime & Manga"),

    @SerializedName("Entertainment: Cartoon & Animations")
    Entertainment_Cartoon_Animations("Entertainment: Cartoon & Animations");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    // Optionally and/or additionally, toString.
    @Override public String toString() { return displayName; }

    public static Category fromDisplayName(String displayName )
    {
        Category[] As = Category.values();
        for(int i = 0; i < As.length; i++)
        {
            if((As[i].displayName())==displayName)
                return As[i];
        }
        return Category.Any;
    }

    public static int getId(Category category){
        switch (category){
            case General_Knowledge:{
                return 9;
            }
            case Entertainment_Books:{
                return 10;
            }
            case Entertainment_Film:{
                return 11;
            }
            case Entertainment_Music:{
                return 12;
            }
            case Entertainment_Musicals_Theatres:{
                return 13;
            }
            case Entertainment_Television:{
                return 14;
            }
            case Entertainment_Video_Games:{
                return 15;
            }
            case Entertainment_Board_Games:{
                return 16;
            }
            case Science_Nature:{
                return 17;
            }
            case Science_Computers:{
                return 18;
            }
            case Science_Mathematics:{
                return 19;
            }
            case Mythology:{
                return 20;
            }
            case Sports:{
                return 21;
            }
            case Geography:{
                return 22;
            }
            case History:{
                return 23;
            }
            case Politics:{
                return 24;
            }
            case Art:{
                return 25;
            }
            case Celebrities:{
                return 26;
            }
            case Animals:{
                return 27;
            }
            case Vehicles:{
                return 28;
            }
            case Entertainment_Comics:{
                return 29;
            }
            case Science_Gadgets:{
                return 30;
            }
            case Entertainment_Japanese_Anime_Manga:{
                return 31;
            }
            case Entertainment_Cartoon_Animations:{
                return 32;
            }
            default:{
                return 0;
            }
        }
    }
}
