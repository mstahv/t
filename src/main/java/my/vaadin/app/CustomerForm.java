package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;

public class CustomerForm extends CustomerFormDesign {
	
	CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private MyUI myUI;
	
	public CustomerForm(MyUI myUI) {
		this.myUI = myUI;
		save.addClickListener(e->this.save());
		delete.addClickListener(e->this.delete());
		status.addItems(CustomerStatus.values());
		save.setClickShortcut(KeyCode.ENTER);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		// Show delete button for only customers already in the database
		delete.setVisible(customer.isPersisted());
		BeanFieldGroup.bindFieldsUnbuffered(customer, this);
		setVisible(true);
		firstName.selectAll();
	}
	
	private void delete() {
		service.delete(customer);
		myUI.updateList();
		setVisible(false);
	}

	protected void save() {
		service.save(customer);
		myUI.updateList();
		setVisible(false);
	}

}
