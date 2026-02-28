package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection dbLink = null;
        Statement dbSt = null;
        try {
            dbLink = Util.getInstance().connectToDb();
            try {
                dbSt = dbLink.createStatement();
                dbSt.executeUpdate("CREATE TABLE IF NOT EXISTS usersTable (" +
                        "id BIGINT(25) NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(25)," +
                        "lastname VARCHAR(25)," +
                        "age TINYINT," +
                        "PRIMARY KEY (id))");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(dbSt != null) {
                    dbSt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbLink != null) {
                    dbLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection dbLink = null;
        Statement dbSt = null;
        try {
            dbLink = Util.getInstance().connectToDb();
            try {
                dbSt = dbLink.createStatement();
                dbSt.executeUpdate("DROP TABLE IF EXISTS usersTable");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(dbSt != null) {
                    dbSt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbLink != null) {
                    dbLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection dbLink = null;
        PreparedStatement dbSt = null;
        try {
            dbLink = Util.getInstance().connectToDb();
            dbLink.setAutoCommit(false);
            try {

                dbSt = dbLink.prepareStatement("INSERT INTO usersTable" +
                        "(name, lastname, age)" +
                        "VALUES" +
                        "(?, ?, ?)");
                dbSt.setString(1, name);
                dbSt.setString(2, lastName);
                dbSt.setByte(3, age);

                dbSt.execute();
                dbLink.commit();
                System.out.println("User c именем - " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                e.printStackTrace();
                dbLink.rollback();
            } finally {
                if(dbSt != null) {
                    dbSt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbLink != null) {
                    dbLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection dbLink = null;
        Statement dbSt = null;
        try {
            dbLink = Util.getInstance().connectToDb();
            try {
                dbSt = dbLink.createStatement();
                dbSt.executeUpdate("DELETE FROM usersTable " +
                        "WHERE id=" + id);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(dbSt != null) {
                    dbSt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbLink != null) {
                    dbLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List <User> listOfUsers = new LinkedList<>();
        Connection dbLink = null;
        Statement dbSt = null;
        ResultSet dbRs = null;
        try {
            dbLink = Util.getInstance().connectToDb();
            try {
                dbSt = dbLink.createStatement();
                try {
                    dbRs = dbSt.executeQuery("SELECT * FROM usersTable");
                    while(dbRs.next()) {
                        User user = new User(dbRs.getString("name"),dbRs.getString("lastname"),dbRs.getByte("age"));
                        user.setId(dbRs.getLong("id"));
                        listOfUsers.add(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if(dbRs != null) {
                        dbRs.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(dbSt != null) {
                    dbSt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbLink != null) {
                    dbLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        Connection dbLink = null;
        Statement dbSt = null;
        try {
            dbLink = Util.getInstance().connectToDb();
            try {
                dbSt = dbLink.createStatement();
                dbSt.executeUpdate("TRUNCATE TABLE usersTable");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(dbSt != null) {
                    dbSt.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbLink != null) {
                    dbLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
