package FrierenMod.effects;

import FrierenMod.events.rooms.ReadingGrimoireEventRoom;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ReadingGrimoireEffect extends AbstractGameEffect {
    public void update() {
        this.isDone = true;
        RoomEventDialog.optionList.clear();
        MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
        MapRoomNode node = new MapRoomNode(currNode.x, currNode.y);
        node.setRoom(new ReadingGrimoireEventRoom());
        for (MapEdge e : currNode.getEdges())
            node.addEdge(e);
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.setCurrMapNode(node);
        AbstractDungeon.getCurrRoom().onPlayerEntry();
        AbstractDungeon.scene.nextRoom(node.room);
        AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
