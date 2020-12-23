package com.zohaib.trivia.constants;

import com.google.gson.annotations.SerializedName;

public enum QuestionType {

    @SerializedName("multiple")
    Multiple("multiple"),

    @SerializedName("boolean")
    Boolean("boolean");

    private String displayName;

    QuestionType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    public static QuestionType fromDisplayName(String displayName )
    {
        QuestionType[] As = QuestionType.values();
        for(int i = 0; i < As.length; i++)
        {
            if((As[i].displayName())==displayName)
                return As[i];
        }
        return QuestionType.Multiple;
    }
}
