package com.aht.bonappettit.domain.relationship;

import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;
import com.aht.neo4j.model.relationship.Event;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;

@RelationshipEntity(type = "CLICKED")
public class Click implements Event {
	@GraphId
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Dish dish;
	private String on;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}
}