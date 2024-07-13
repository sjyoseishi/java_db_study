package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

    public static void main(String[] args) {
    	
        Connection con = null;
        Statement statement = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                ""
            );
            System.out.println("データベース接続成功：" + con);
            
            // SQLクエリを準備
            statement = con.createStatement();
            String update_sql = "UPDATE scores SET score_math = 95,  score_english = 80 WHERE id = 5;";

            // SQLクエリを実行（DBMSに送信）
            System.out.println("レコード更新を実行します");
            int rowCnt = statement.executeUpdate(update_sql);
            System.out.println( rowCnt + "件のレコードが更新されました");
            
            //数学・英語の点数が高い順に並べ替えます
            String order_sql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
            ResultSet result = statement.executeQuery(order_sql);
            System.out.println("数学・英語の点数が高い順に並べ替えました");
         // SQLクエリの実行結果を抽出
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int score_math = result.getInt("score_math");
                int score_english = result.getInt("score_english");
                System.out.println(result.getRow() + "生徒ID=" + id
                                   + "／氏名=" + name + "／数学=" + score_math 
                                   + "／英語=" + score_english);
            }
            
        } catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 使用したオブジェクトを解放
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
        }
    }
}