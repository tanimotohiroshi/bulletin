package bulletinBoard.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bulletinBoard.exception.IORuntimeException;
import bulletinBoard.exception.SQLRuntimeException;

public class CloseableUtil {

	public static void close(Closeable closeable) {

		if (closeable == null) {
			return;
		}

		try {
			closeable.close();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(Connection connection) {
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void close(Statement ps) {
		if (ps == null) {
			return;
		}
		try {
			ps.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void close(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}
