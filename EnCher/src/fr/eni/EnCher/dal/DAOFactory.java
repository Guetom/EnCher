package fr.eni.EnCher.dal;

public abstract class DAOFactory {
	
	public static EnCherDAO getEnCherDAO()
	{
		return new EnCherDAOJdbcImpl();
	}
}