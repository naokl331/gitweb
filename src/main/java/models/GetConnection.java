package models;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.postgresql.Driver;

public class GetConnection {
	//フィールド
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	//connectionのオープン用  connectionのインスタンスを作りたいだけ　そのためにドライバがいるような感じ
	public void open() {
		try {
			//ドライバーのインスタンス化
			Driver.class.getDeclaredConstructor().newInstance();
			//Connectionのインスタンス化 //jdbc接続でpostgresqlにつなぐ ://ホスト名orIPアドレス:ポート番号/DB名
			con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/katodo","postgres","postgres");
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	//オープンしているオブジェクトをすべてクローズ
	public void close() {
		//rsは存在したらclose
		try {
			if(Objects.isNull(rs)) rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
