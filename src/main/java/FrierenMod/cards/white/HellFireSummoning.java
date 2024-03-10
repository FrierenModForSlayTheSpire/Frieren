package FrierenMod.cards.white;

import FrierenMod.actions.FastMoveSpecificCardToExhaustAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.powers.SealPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HellFireSummoning extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(HellFireSummoning.class.getSimpleName());
    public HellFireSummoning() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 15;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int times = LegendMagicHelper.getChantCardUsedThisTurn();
        if (p.hasRelic("Chemical X")) {
            times += 2;
            p.getRelic("Chemical X").flash();
        }
        for (int i = 0; i < times; i++) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard cardPlayed) {
        if(cardPlayed instanceof AbstractFrierenCard && ((AbstractFrierenCard) cardPlayed).isChantCard)
            this.superFlash();
    }

    public void atBattleStart(){
        this.SealThisCard();
    }
    private void MoveThisCardToExhaustInCardGroup(CardGroup cardGroup){
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof HellFireSummoning){
                this.addToBot(new FastMoveSpecificCardToExhaustAction(c,cardGroup));
                if(cardGroup == AbstractDungeon.player.hand){
                    this.addToBot(new DrawCardAction(1));
                }
                this.addToBot(new ApplyPowerAction(p,p,new SealPower(p,1),1));
            }
        }
    }
    private void SealThisCard(){
        AbstractPlayer p = AbstractDungeon.player;
        this.MoveThisCardToExhaustInCardGroup(p.drawPile);
        this.MoveThisCardToExhaustInCardGroup(p.discardPile);
        this.MoveThisCardToExhaustInCardGroup(p.hand);
        for(AbstractCard c : p.exhaustPile.group){
            if(c instanceof HellFireSummoning){
                p.exhaustPile.removeCard(c);
            }
        }
    }
}
