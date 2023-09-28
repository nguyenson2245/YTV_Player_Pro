package com.techBMT.YTV_Player_Pro.itf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface IFragmentParent {
    List<File> getDataFiles();
    void updateListSort(ArrayList<File> listSort);
}
