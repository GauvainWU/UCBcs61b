package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        this.energy = e;
        r = 34;
        g = 0;
        b = 231;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Creature replicate() {
        this.energy *= 0.5;
        return new Clorus(this.energy);
    }

    /**
     * If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE to a random empty square.
     * @param neighbors A hashmap storing the occupant at a certain direction.
     * @return Action to be taken.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyDirections = new ArrayDeque<>();
        Deque<Direction> plipDirections = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            String neighbour = neighbors.get(d).name();
            if (neighbour == "empty") {
                emptyDirections.add(d);
            } else if (neighbour == "plip") {
                plipDirections.add(d);
            }
        }

        if (!plipDirections.isEmpty()){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipDirections));
        }

        if (emptyDirections.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }

        if (this.energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyDirections));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyDirections));
    }

    @Override
    public Color color() {
        return color(r,g,b);
    }
}

