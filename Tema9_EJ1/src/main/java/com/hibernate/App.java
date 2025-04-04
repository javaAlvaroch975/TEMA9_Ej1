package com.hibernate;

import java.util.List;
import com.hibernate.dao.CiudadDao;
import com.hibernate.model.Ciudad;
import java.util.Scanner;

public class App {
	static void imprimirMenu() {
		System.out.println("1 Insertar ciudad");
		System.out.println("2 Borrar ciudad");
		System.out.println("3 Actualizar nombre ciudad");
		System.out.println("4 Actualizar habitantes ciudad");
		System.out.println("5 Ver ciudades");
		System.out.println("6 Ver datos ciudad");
		System.out.println("7 Ver ciudades con mas de 1M de habitantes");
		System.out.println("0 → Salir");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opc = 0;
		int code = 0;
		int numhabitantes;
		String nombre;

		CiudadDao ciuDAO = new CiudadDao();
		Ciudad cid1 = new Ciudad();

		do {
			imprimirMenu();
			opc = sc.nextInt();
			switch (opc) {

			case 0:
				System.out.println("Hasta pronto");
				break;

			//	INSERTAR
			case 1:
				System.out.println("Dime el nombre");
				nombre = sc.next();

				System.out.println("Dime el numero de habitantes");
				numhabitantes = sc.nextInt();

				cid1 = new Ciudad(nombre, numhabitantes);
				ciuDAO.insertCiudad(cid1);
				ciuDAO.updateCiudad(cid1);
				System.out.println();

				break;
				
				//BORRAR
			case 2:
				System.out.println("Dime el codigo");
				code = sc.nextInt();

				ciuDAO.selectCiudadBYID(code);
				ciuDAO.deleteCiudad(code);
				System.out.println();

				break;

				//ACTUALIZAR NOMBRE MEDIANTE CODE
			case 3:
				System.out.println("Dime el ID");
				code = sc.nextInt();

				System.out.println("Dime el nombre");
				nombre = sc.next();

				cid1 = ciuDAO.selectCiudadBYID(code);
				cid1.setNombre(nombre);
				ciuDAO.updateCiudad(cid1);
				System.out.println();

				break;

				//ACTUALIZAR N_HABITANTES MEDIANTE CODE
			case 4:
				System.out.println("Dime el ID");
				code = sc.nextInt();

				System.out.println("Dime el numero de habitantes");
				numhabitantes = sc.nextInt();

				ciuDAO.selectCiudadBYID(code);
				cid1.getNum_habitantes();
				cid1.setNum_habitantes(numhabitantes);
				ciuDAO.updateCiudad(cid1);
				System.out.println();

				break;

				//RECORRER CIUDADES
			case 5:
				List<Ciudad> ciudades = ciuDAO.selectAllCiudad();
				for (Ciudad p : ciudades) {
					System.out.println(p.getNombre() + " " + p.getId() + " " + p.getNum_habitantes());
				}
				
				break;

				//CONSEGUIR CIUDAD ESPECIFICA
			case 6:
				System.out.println("Dime el ID");
				code = sc.nextInt();
				cid1=ciuDAO.selectCiudadBYID(code);
				System.out.println(cid1.getNombre());
				System.out.println();
				break;

				//CONSEGUIR CIUDADES CON MAS DE 1M DE HABITANTES
			case 7:
				List<Ciudad> ciudades_1M = ciuDAO.selectAllCiudad();
				for (Ciudad p : ciudades_1M) {
					if (p.getNum_habitantes() > 1000000) {
						System.out.println(p.getNombre() + " " + p.getId() + " " + p.getNum_habitantes());
					}
				}
				System.out.println();
				
				break;
				
			case 8:
				System.out.println("Dime un numero");
				int numero = sc.nextInt();
				List<Ciudad> ciudades_usu = ciuDAO.selectAllCiudad();

				for (Ciudad p : ciudades_usu) {
					if (p.getNum_habitantes() < numero) {
						System.out.println(p.getNombre() + " " + p.getId() + " " + p.getNum_habitantes());
					}
				}
				System.out.println();
				
				break;
				
			case 9:
				System.out.println("Dime el nombre");
				String nombre_usu = sc.next();
				
				
				break;
				
			default:
				System.out.println("Opción inválida");

				break;

			}

		} while (opc != 0);

	}

}
