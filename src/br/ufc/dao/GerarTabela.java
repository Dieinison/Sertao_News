package br.ufc.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerarTabela {
	public static void main(String[] args) {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("jornal_web");
		factory.close();
	}
}
