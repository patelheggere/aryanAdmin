package com.example.admin.aryanadmin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MCQQuestionModel implements Parcelable {
    private int mQuestionNo;
    private String mQuestion;
    private Options options;
    private int mCorrectAns;
    private boolean isAnswered;
    private boolean isViewed;
    private int mNoOfOptions;
    private int mQType;
    private int mUserAnswer = -1;

    public MCQQuestionModel() {
    }

    public MCQQuestionModel(int mQuestionNo, String mQuestion, Options options, int mCorrectAns, boolean isAnswered, boolean isViewed, int mNoOfOptions, int mQType, int mUserAnswer) {
        this.mQuestionNo = mQuestionNo;
        this.mQuestion = mQuestion;
        this.options = options;
        this.mCorrectAns = mCorrectAns;
        this.isAnswered = isAnswered;
        this.isViewed = isViewed;
        this.mNoOfOptions = mNoOfOptions;
        this.mQType = mQType;
        this.mUserAnswer = mUserAnswer;
    }

    protected MCQQuestionModel(Parcel in) {
        mQuestionNo = in.readInt();
        mQuestion = in.readString();
        mCorrectAns = in.readInt();
        isAnswered = in.readByte() != 0;
        isViewed = in.readByte() != 0;
        mNoOfOptions = in.readInt();
        mQType = in.readInt();
        mUserAnswer = in.readInt();
    }

    public static final Creator<MCQQuestionModel> CREATOR = new Creator<MCQQuestionModel>() {
        @Override
        public MCQQuestionModel createFromParcel(Parcel in) {
            return new MCQQuestionModel(in);
        }

        @Override
        public MCQQuestionModel[] newArray(int size) {
            return new MCQQuestionModel[size];
        }
    };

    public int getmQuestionNo() {
        return mQuestionNo;
    }

    public void setmQuestionNo(int mQuestionNo) {
        this.mQuestionNo = mQuestionNo;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public int getmCorrectAns() {
        return mCorrectAns;
    }

    public void setmCorrectAns(int mCorrectAns) {
        this.mCorrectAns = mCorrectAns;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    public int getmNoOfOptions() {
        return mNoOfOptions;
    }

    public void setmNoOfOptions(int mNoOfOptions) {
        this.mNoOfOptions = mNoOfOptions;
    }

    public int getmQType() {
        return mQType;
    }

    public void setmQType(int mQType) {
        this.mQType = mQType;
    }

    public int getmUserAnswer() {
        return mUserAnswer;
    }

    public void setmUserAnswer(int mUserAnswer) {
        this.mUserAnswer = mUserAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mQuestionNo);
        parcel.writeString(mQuestion);
        parcel.writeInt(mCorrectAns);
        parcel.writeByte((byte) (isAnswered ? 1 : 0));
        parcel.writeByte((byte) (isViewed ? 1 : 0));
        parcel.writeInt(mNoOfOptions);
        parcel.writeInt(mQType);
        parcel.writeInt(mUserAnswer);
    }

    public static class Options{
        private String op1;
        private String op2;
        private String op3;
        private String op4;
        private String op5;
        private String op6;

        public Options() {
        }

        public Options(String op1, String op2, String op3, String op4, String op5, String op6) {
            this.op1 = op1;
            this.op2 = op2;
            this.op3 = op3;
            this.op4 = op4;
            this.op5 = op5;
            this.op6 = op6;
        }

        public String getOp1() {
            return op1;
        }

        public void setOp1(String op1) {
            this.op1 = op1;
        }

        public String getOp2() {
            return op2;
        }

        public void setOp2(String op2) {
            this.op2 = op2;
        }

        public String getOp3() {
            return op3;
        }

        public void setOp3(String op3) {
            this.op3 = op3;
        }

        public String getOp4() {
            return op4;
        }

        public void setOp4(String op4) {
            this.op4 = op4;
        }

        public String getOp5() {
            return op5;
        }

        public void setOp5(String op5) {
            this.op5 = op5;
        }

        public String getOp6() {
            return op6;
        }

        public void setOp6(String op6) {
            this.op6 = op6;
        }
    }
}
