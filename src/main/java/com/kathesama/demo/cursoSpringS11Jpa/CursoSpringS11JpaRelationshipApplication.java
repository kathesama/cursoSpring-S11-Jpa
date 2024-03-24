package com.kathesama.demo.cursoSpringS11Jpa;

import com.kathesama.demo.cursoSpringS11Jpa.entitiy.Address;
import com.kathesama.demo.cursoSpringS11Jpa.entitiy.Client;
import com.kathesama.demo.cursoSpringS11Jpa.entitiy.ClientDetails;
import com.kathesama.demo.cursoSpringS11Jpa.entitiy.Invoice;
import com.kathesama.demo.cursoSpringS11Jpa.repository.ClientRepository;
import com.kathesama.demo.cursoSpringS11Jpa.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class CursoSpringS11JpaRelationshipApplication implements CommandLineRunner {
	private final ClientRepository clientRepository;

	private final InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringS11JpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oneToOneBidireccionalFindById();
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> clientOptional = clientRepository.findOne(1L);
		clientOptional.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);

			client.setClientDetails(clientDetails);

			clientRepository.save(client);

			System.out.println(client);
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("compras de la casa", 5000D);
		Invoice invoice2 = new Invoice("compras de oficina", 8000D);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToMany(){
//		Client client = Client.builder().name("Fran").lastname("Moras").build();
		Client client = new Client("Fran", "Moras");


		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {
//		Client client = new Client(null, "John", "Doe", null, null);
		Client client = Client.builder().name("John").lastname("Doe").build();
		System.out.println(client.toString());
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000D);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("compras de oficina", 2000D);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}
}
