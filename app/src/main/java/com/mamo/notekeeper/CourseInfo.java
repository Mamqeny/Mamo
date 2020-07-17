package com.mamo.notekeeper;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class CourseInfo implements Parcelable {
    private final String mCourseId;
    private final String mTitle;
    private final List<ModuleInfo> mModules;

    public CourseInfo(String courseId, String title, java.util.List<ModuleInfo> modules) {
        mCourseId = courseId;
        mTitle = title;
        mModules = modules;
    }

    private CourseInfo(android.os.Parcel source) {
        mCourseId = source.readString();
        mTitle = source.readString();
        mModules = new java.util.ArrayList<>();
        source.readTypedList(mModules, ModuleInfo.CREATOR);
    }

    public String getCourseId() {
        return mCourseId;
    }

    public String getTitle() {
        return mTitle;
    }

    public java.util.List<ModuleInfo> getModules() {
        return mModules;
    }

    public boolean[] getModulesCompletionStatus() {
        boolean[] status = new boolean[mModules.size()];

        for(int i=0; i < mModules.size(); i++)
            status[i] = mModules.get(i).isComplete();

        return status;
    }

    public void setModulesCompletionStatus(boolean[] status) {
        for(int i=0; i < mModules.size(); i++)
            mModules.get(i).setComplete(status[i]);
    }

    public ModuleInfo getModule(String moduleId) {
        for(ModuleInfo moduleInfo: mModules) {
            if(moduleId.equals(moduleInfo.getModuleId()))
                return moduleInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        com.mamo.notekeeper.CourseInfo that = (com.mamo.notekeeper.CourseInfo) o;

        return mCourseId.equals(that.mCourseId);

    }

    @Override
    public int hashCode() {
        return mCourseId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(mCourseId);
        dest.writeString(mTitle);
        dest.writeTypedList(mModules);
    }

    public static final android.os.Parcelable.Creator<CourseInfo> CREATOR =
            new android.os.Parcelable.Creator<CourseInfo>() {

                @Override
                public com.mamo.notekeeper.CourseInfo createFromParcel(android.os.Parcel source) {
                    return new com.mamo.notekeeper.CourseInfo(source);
                }

                @Override
                public com.mamo.notekeeper.CourseInfo[] newArray(int size) {
                    return new com.mamo.notekeeper.CourseInfo[size];
                }
            };

}
