package fr.eni.EnCher.dal;

import java.util.List;

import fr.eni.EnCher.exception.EncherException;

public interface DAO<T> {	
	public List<T> selectionner(Lister choix) throws EncherException;
	public void ajouter(T t) throws EncherException;
	public void modifier(T t) throws EncherException;
	public void supprimer(T t) throws EncherException;
	
}
