package com.zohaib.trivia.constants;

import com.google.gson.annotations.SerializedName;

public enum Difficulty {

    @SerializedName("easy")
    Easy("easy"),

    @SerializedName("medium")
    Medium("medium"),

    @SerializedName("hard")
    Hard("hard");

    private String displayName;

    Difficulty(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    public static Difficulty fromDisplayName(String displayName )
    {
        Difficulty[] As = Difficulty.values();
        for(int i = 0; i < As.length; i++)
        {
            if((As[i].displayName())==displayName)
                return As[i];
        }
        return Difficulty.Easy;
    }
}
