package com.example.owi.wallet;


import com.example.owi.wallet.activities.Add;
import com.example.owi.wallet.activities.AddCategory;
import com.example.owi.wallet.activities.AddLimit;
import com.example.owi.wallet.activities.MainActivity;
import com.example.owi.wallet.activities.Show;

public class MenuHelper {



    public static Class onMenuItemSelected( int id ){

        Class selectedClass = null;


        switch(id){
            case (R.id.action_settings1):
                selectedClass = MainActivity.class;
                break;
            case (R.id.action_settings2):
                selectedClass = Add.class;
                break;
            case (R.id.action_settings3):
                selectedClass = Show.class;
                break;
            case (R.id.action_settings4):
                selectedClass = AddLimit.class;
                break;
            case (R.id.action_settings6):
                selectedClass = AddCategory.class;
                break;

        }

        return selectedClass;
    }


}
