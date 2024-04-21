/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.postTbl;

import g2.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author APC
 */
public class postDAO {

    public List<postDTO> getPostByTopic(int topic_id) throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<postDTO> list_post = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM postTbl Where topic_id = ?";
                stm = con.prepareCall(query);
                stm.setInt(1, topic_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int post_id = rs.getInt("post_id");
                    int user_id = rs.getInt("user_id");
                    //topic_id
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    boolean isHidden = rs.getBoolean("isHidden");
                    boolean isDelete = rs.getBoolean("isDelete");
                    String image = rs.getString("image");
                    list_post.add(new postDTO(post_id, user_id, topic_id, title, content, isHidden, isDelete, image));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return list_post;
    }

    public List<postDTO> getPostData() throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<postDTO> list_post = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM postTbl";
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int post_id = rs.getInt("post_id");
                    int user_id = rs.getInt("user_id");
                    int topic_id = rs.getInt("topic_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    boolean isHidden = rs.getBoolean("isHidden");
                    boolean isDelete = rs.getBoolean("isDelete");
                    String image = rs.getString("image");
                    list_post.add(new postDTO(post_id, user_id, topic_id, title, content, isHidden, isDelete, image));
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list_post;
    }
}
