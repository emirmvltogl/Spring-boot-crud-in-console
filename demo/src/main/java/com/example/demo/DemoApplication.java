package com.example.demo;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Repo.UserRepo;
import com.example.demo.UserEntity.User;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	UserRepo repo;

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {

			mainMethod();

		};
	}

	private void mainMethod() {
		Scanner scanner = new Scanner(System.in);
		String menu = "please press choise number for database operation \n q-quit\n 1-Create User\n 2-Get All Data\n 3- find User by Id\n 4- Sort all user \n 5-Update Data\n 6-Delete user from database table\n 7-Delete All Data";
		while (true) {
			System.out.println(menu);
			String answer = scanner.nextLine();
			if (answer.equals("q")) {
				break;
			} else if (answer.equals("1")) {
				save(repo);
			} else if (answer.equals("2")) {
				getAllUser(repo);
			} else if (answer.equals("3")) {
				findByIdUser(repo);
			} else if (answer.equals("4")) {
				sortAllUsers(repo);
			} else if (answer.equals("5")) {
				updateUser(repo);
			} else if (answer.equals("6")) {
				deleteUser(repo);
			} else if (answer.equals("7")) {
				deleteAll(repo);
			}

		}
	}

	private void deleteAll(UserRepo repo) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("are you want delete all user?");
		String answer = scanner.nextLine();
		if (answer.equals("no")) {
			System.out.println("okey no problem");
		} else {
			int numberOfDeletedRows = repo.deleteAll();
			System.out.println("Deleted Rows : " + numberOfDeletedRows);
		}
	}

	private void deleteUser(UserRepo repo) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Enter your want to deleted user id");
		int id = scanner.nextInt();
		scanner.nextLine();
		User user = repo.findById(id);
		System.out.println("deleted user is : " + user);
		System.out.println("Are you want to delete?");
		String answer = scanner.nextLine();
		if (answer.equals("no")) {
			System.out.println("User is not deleted.");
		} else {
			repo.delete(id);
			System.out.println("Delete Operation is succesfuly...");
		}

	}

	private void updateUser(UserRepo repo) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Enter want to updating User id");
		int id = scanner.nextInt();
		scanner.nextLine();
		User user = repo.findById(id);
		System.out.println("User is found : " + user);
		System.out.println("please enter your want to updated fields");
		String answer = scanner.nextLine();
		if (answer.equals("firstName")) {
			System.out.println("Please Enter new First Name");
			String name = scanner.nextLine();
			user.setFirstName(name);
			repo.update(user);
			System.out.println("Operation Succesfuly new user : " + user);
		}
		if (answer.equals("lastName")) {
			System.out.println("Please Enter new Last Name");
			String name = scanner.nextLine();
			user.setLastName(name);
			repo.update(user);
			System.out.println("Operation Succesfuly new user : " + user);
		} else if (answer.equals("email")) {
			System.out.println("Please Enter new Email");
			String name = scanner.nextLine();
			user.setEmail(name);
			repo.update(user);
			System.out.println("Operation Succesfuly new user : " + user);

		} else {
			System.out.println("False Answer Exception");
		}
	}

	private void sortAllUsers(UserRepo repo) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Sorting type ex : firstName , lastName , email, id ");
		String field = scan.nextLine();

		List<User> theList = repo.sortAllUser(field);
		for (User user : theList) {
			System.out.println(user);
		}
	}

	private void findByIdUser(UserRepo usRepo) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("please enter your searching id...");
		int id = scanner.nextInt();
		System.out.println(usRepo.findById(id));
	}

	private void getAllUser(UserRepo repo) {
		// add list to gettAll methods
		List<User> theList = repo.getAll();
		// display list
		for (User user : theList) {
			System.out.println(user);
		}
	}

	public void save(UserRepo userRepo) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("please enter your name");
		String firstName = scanner.nextLine();
		System.out.println("Please enter your lastName");
		String lastName = scanner.nextLine();
		System.out.println("Please Enter your Email adress...");
		String email = scanner.nextLine();
		User theUser = new User(firstName, lastName, email);
		userRepo.save(theUser);
		System.out.println("User save operation is succesfuly...");
	}

}