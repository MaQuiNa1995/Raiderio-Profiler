package maquina1995.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
@Route("")
public class ExampleController extends VerticalLayout {

	public ExampleController() {
		add(new Button("¡Hola desde Vaadin!"));
	}
}
