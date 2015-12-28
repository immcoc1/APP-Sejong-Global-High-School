package org.androidtown.sample130;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 2015-08-19.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    final static public String DATABASE_TABLE_NAME = "LDB_TABLE";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("logi", "Database creating..");
        createTable(db);
        insertData(db);
        Log.i("logi", "Database created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        Log.i("logi", "Database Upgrading...");
        String sql = "drop table if exists " + DATABASE_TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
        Log.i("logi", "Database Upgraded");

    }

    public void createTable(SQLiteDatabase db) {

        Log.i("logi", "Table Creating....");
        String sql = "create table " + DATABASE_TABLE_NAME
                + "( name text,"
                + " author text,"
                + " genre text,"
                + " isBookmarked integer )";

        try {
            db.execSQL(sql);
            Log.i("logi", "Table " + DATABASE_TABLE_NAME + " Created");
        } catch (Exception e) {
            Log.i("logi", "Error in creating table: " + e.getMessage());
        }


    }

    public void insertData(SQLiteDatabase db) {

        Log.i("logi", "Table Inserting Data...");

        db.beginTransaction();

        try {

            myInsert(db, "공무도하가", " ", "고대가요"); //Ok
            myInsert(db, "구지가", " ", "고대가요"); //ok
            myInsert(db, "정읍사", " ", "고대가요"); //ok
            myInsert(db, "해가", " ", "고대가요"); //ok
            myInsert(db, "황조가", "유리왕", "고대가요"); //ok


            //--------------------------------------------------

            myInsert(db, "도솔가", "월명사", "향가"); //ok
            myInsert(db, "도천수대비가", "희명", "향가");//ok
            myInsert(db, "모죽지랑가", "득오곡", "향가");
            myInsert(db, "서동요", "서동(무왕)", "향가");
            myInsert(db, "안민가", "충담사", "향가");
            //myInsert(db, "우적가", "영재", "향가");
            myInsert(db, "원왕생가", "광덕", "향가");
            myInsert(db, "제망매가", "월명사", "향가");
            myInsert(db, "찬기파랑가", "충담사", "향가");
            myInsert(db, "처용가", "처용", "향가");
            myInsert(db, "헌화가", " ", "향가");
            // myInsert(db, "혜성가", "융천사", "향가");

            //----------------------------------------------------

            myInsert(db, "여수장우중문시", "을지문덕", "한시 - 상고시대");
            myInsert(db, "제가야산독서당", "최치원", "한시 - 상고시대");
            myInsert(db, "추야우중", "최치원", "한시 - 상고시대");

            //-----------------------------------------------------

            myInsert(db, "가시리", " ", "고려가요");
            myInsert(db, "동동", " ", "고려가요");
            myInsert(db, "만전춘별사", " ", "고려가요");
            myInsert(db, "서경별곡", " ", "고려가요");
            myInsert(db, "정과정", "정서", "고려가요");
            myInsert(db, "정석가", " ", "고려가요");
            myInsert(db, "청산별곡", " ", "고려가요");

            //---------------------------------------------------

            //myInsert(db, "관동별곡(경기체가)", "안축", "경기체가");
            //myInsert(db, "기우목동가", "지은", "경기체가");
            //myInsert(db, "상대별곡", "권근", "경기체가");
            myInsert(db, "한림별곡", "한림의 유생들", "경기체가");

            //------------------------------------------------------

            myInsert(db, "동명왕편", "이규보", "한시 - 고려");
            myInsert(db, "부벽루", "이색", "한시 - 고려");
            myInsert(db, "사리화", "이제현", "한시 - 고려");
            myInsert(db, "산거", "이인로", "한시 - 고려");
            myInsert(db, "송인", "정지상", "한시 - 고려");
            myInsert(db, "시벽", "이규보", "한시 - 고려");

            //---------------------------------------------------------

            myInsert(db, "구룸이 무신탄 말이", "이존오", "시조 - 고려");
            myInsert(db, "백설이 자자진 골에", "이색", "시조 - 고려");
            myInsert(db, "이 몸이 주거 주거", "정몽주", "시조 - 고려");
            myInsert(db, "이런들 엇더하며", "이방원", "시조 - 고려");
            myInsert(db, "이화에 월백하고", "이조년", "시조 - 고려");
            myInsert(db, "춘산에 눈 녹인 바람", "우탁", "시조 - 고려");
            myInsert(db, "한 손에 막대 잡고", "우탁", "시조 - 고려");
            //myInsert(db, "가마귀 싸호난 골에", "정몽주의 어머니","평시조");

            //----------------------------------------------------------

            myInsert(db, "용비어천가", " ", "악장");

            //----------------------------------------------------------

            myInsert(db, "가마귀 검다하고", "이직", "시조 - 조선 전기");
            myInsert(db, "간 밤에 부던 바람에", "유응부", "시조 - 조선 전기");
            myInsert(db, "고인도 날 못 보고", "이황", "시조 - 조선 전기");
            myInsert(db, "내 언제 무신하여", "황진이", "시조 - 조선 전기");
            myInsert(db, "동지 섯달 기나긴 밤을", "황진이", "시조 - 조선 전기");
            myInsert(db, "마음이 어린 후니", "서경덕", "시조 - 조선 전기");
            myInsert(db, "말 없는 청산이요", "성흔", "시조 - 조선 전기");
            myInsert(db, "묏버들 가려 꺾어", "홍랑", "시조 - 조선 전기");
            myInsert(db, "방 안에 켠 촉불", "이개", "시조 - 조선 전기");
            myInsert(db, "삭풍은 나무 끝에 불고", "김종서", "시조 - 조선 전기");
            myInsert(db, "수양산 바라보며", "성삼문", "시조 - 조선 전기");
            myInsert(db, "십 년을 경영하여", "송순", "시조 - 조선 전기");
            myInsert(db, "어져 내 일이야", "황진이", "시조 - 조선 전기");
            myInsert(db, "오백 년 도읍지를", "길재", "시조 - 조선 전기");
            myInsert(db, "이 몸이 죽어 가서", "성삼문", "시조 - 조선 전기");
            myInsert(db, "이화우 흩뿌릴 제", "계랑", "시조 - 조선 전기");
            myInsert(db, "천만 리 머나먼 길에", "왕방연", "시조 - 조선 전기");
            myInsert(db, "청산리 벽계수야", "황진이", "시조 - 조선 전기");
            myInsert(db, "흥망이 유수하니", "원천석", "시조 - 조선 전기");

            //myInsert(db, "선인교 나린 물이", "정도전", "평시조");
            //myInsert(db, "눈 마자 휘여진 대룰", "원천석", "평시조");
            //myInsert(db, "가마귀 눈비 마자", "박팽년", "평시조");
            //myInsert(db, "풍상이 섯거 친 날에", "송순", "평시조");
            //myInsert(db, "삼동에 뵈옷 닙고", "조식", "평시조");
            //myInsert(db, "가마귀 검다 하고", "이직", "평시조");
            // myInsert(db, "마음아 너난 어이", "서경덕", "평시조");
            //  myInsert(db, "대쵸 볼 불근", "황희", "평시조");
            // myInsert(db, "추강에 밤이 드니", "월산대군", "평시조");
            // myInsert(db, "재 너머 성궐롱 집에", "정철", "평시조");
            // myInsert(db, "두류산 양단수를", "조식", "평시조");
            //myInsert(db, "내 언제 신ㄴ이 업서", "황진이", "평시조");
            // myInsert(db, "청초 우거진 골에", "임제", "평시조");
            //myInsert(db, "청산은 내 뜻이오", "황진이", "평시조");

            //---------------------------------------------------------

            myInsert(db, "강호사시가", "맹사성", "연시조 - 조선 전기");
            myInsert(db, "고산구곡가", "이이", "연시조 - 조선 전기");
            myInsert(db, "도산십이곡", "이황", "연시조 - 조선 전기");
            //어부가
            // myInsert(db, "훈민가", "정철", "연시조");

            //--------------------------------------------------------

            myInsert(db, "관동별곡", "정철", "가사 - 조선 전기");
            myInsert(db, "규원가", "허난설헌", "가사 - 조선 전기");
            myInsert(db, "면앙정가", "송순", "가사 - 조선 전기");
            myInsert(db, "사미인곡", "정철", "가사 - 조선 전기");
            myInsert(db, "상춘곡", "정극인", "가사 - 조선 전기");
            myInsert(db, "속미인곡", "정철", "가사 - 조선 전기");

            //------------------------------------------------------

            myInsert(db, "무어별", "임제", "한시 - 조선 전기");
            myInsert(db, "봄비", "허난설헌", "한시 - 조선 전기");

            //-----------------------------------------------------

            myInsert(db, "가노라 삼각산아", "김상헌", "시조 - 조선 후기");
                /*국화야 너는 어이
                님 그린 상사몽이
                반중 조홍감이
                발가버슨 아해들아
                산촌에 눈이 오니
                청산도 절로절로
                한산섬 밝은 달에*/

            //-----------------------------------------------

            myInsert(db, "견회요", "윤선도", "연시조 - 조선 후기");
                /*농가
                        만흥
                매화사
                        어부사시사
                오우가*/

            //myInsert(db, "오륜가", "주세붕", "연시조");

            //-----------------------------------------------

            myInsert(db, "농가월령가", "정학유", "가사 - 조선 후기");
            myInsert(db, "누항사", "박인로", "가사 - 조선 후기");
            myInsert(db, "선상탄", "박인로", "가사 - 조선 후기");
            myInsert(db, "용부가", "작자미상", "가사 - 조선 후기");
            myInsert(db, "일동장유가", "김인겸", "가사 - 조선 후기");
            myInsert(db, "화전가", " ", "가사 - 조선 후기");


                /*myInsert(db, "고공가", "허전", "가사");
                myInsert(db, "고공답주인가", "이원익", "가사");
                myInsert(db, "관등가", "작자미상", "가사");
                myInsert(db, "덴동어미화전가", "작자미상", "가사");
                myInsert(db, "만분가", "조위", "가사");
                myInsert(db, "만언사", "안조원(환)", "가사");
                myInsert(db, "명월음", "최현", "가사");
                myInsert(db, "봉선화가", "작자미상", "가사");
                myInsert(db, "북찬가", "이광명", "가사");
                myInsert(db, "상사곡", "박인로", "가사");
                myInsert(db, "상사별곡", "작자미상", "가사");
                myInsert(db, "성산별곡", "정철", "가사");
                myInsert(db, "연행가", "홍순학", "가사");
                myInsert(db, "우부가", "작자미상", "가사");
                myInsert(db, "월령상사가", "작자미상", "가사");
                myInsert(db, "유산가", "작자미상", "가사");
                myInsert(db, "춘면곡", "작자미상", "가사");
                myInsert(db, "동심가", "이중원", "가사");
                myInsert(db, "탄궁가", "정훈", "가사");*/

            //------------------------------------------------------

            myInsert(db, "개를 여라믄이나 기르되", "작자미상", "사설시조");
            myInsert(db, "개야미 불개야미", "작자미상", "사설시조");
            //myInsert(db, "굼벵이 매암이 되어", "작자미상", "사설시조");
            myInsert(db, "귀또리 져 귀또리", "작자미상", "사설시조");
            myInsert(db, "나무도 바위돌도", "작자미상", "사설시조");
            myInsert(db, "논밭 갈아 기음매고", "작자미상", "사설시조");
            myInsert(db, "님이 오마 하거늘", "작자미상", "사설시조");
            myInsert(db, "댁득에 동난지사오", "작자미상", "사설시조");
            myInsert(db, "두터비 파리를 물고", "작자미상", "사설시조");
            //myInsert(db, "모시를 이리저리 삼아", "작자미상", "사설시조");
            //myInsert(db, "바람도 쉬어 넘는 고개", "작자미상", "사설시조");
            //myInsert(db, "밤은 깊어 삼경에", "작자미상", "사설시조");
            //myInsert(db, "시어마님 며느라기", "작자미상", "사설시조");
            //myInsert(db, "어이 못 오던가", "작자미상", "사설시조");
            myInsert(db, "일신이 사쟈한들", "작자미상", "사설시조");
            myInsert(db, "창을 내고자 창을 내고자", "작자미상", "사설시조");
            //myInsert(db, "창밖이 어른어른하거늘", "작자미상", "사설시조");
            //myInsert(db, "천지간 만물지중에", "작자미상", "사설시조");
            myInsert(db, "한숨아 셰한숨아", "작자미상", "사설시조");
            // myInsert(db, "한 잔 먹세그려 또 한 잔", "정철", "사설시조");


            //------------------------------------------------------------

            myInsert(db, "고시 7", "정약용", "한시 - 조선 후기");
            myInsert(db, "고시 8", "정약용", "한시 - 조선 후기");
                /*무제
                보리타작/타밀행
                삿갓을 읊다
                용산마을 아전/용산리
                        절명시
                탐진촌요*/


            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.i("logi", "Error in inserting data: " + e.getMessage());
        } finally {
            db.endTransaction();
        }


    }

    public void myInsert(SQLiteDatabase db, String name, String author, String genre) {

        //Log.i("logi", "Inserting " + name);
        String sql = "insert into " + DATABASE_TABLE_NAME + "(name, author, genre, isBookmarked) "
                + "values( '" + name + "', '" + author + "', '" + genre + "', 0)";
        db.execSQL(sql);
    }

}
