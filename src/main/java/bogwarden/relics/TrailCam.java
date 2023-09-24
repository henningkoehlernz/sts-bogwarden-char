package bogwarden.relics;

import bogwarden.cards.AbstractTrapCard;
import bogwarden.characters.TheBogwarden;
import bogwarden.powers.EnergizedBogPower;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static bogwarden.BogMod.makeID;
import static bogwarden.util.Wiz.*;

public class TrailCam extends AbstractBogRelic {
    public static final String ID = makeID("TrailCam");
    private static int TRAPS_NEEDED = 5;

    public TrailCam() {
        super(ID, RelicTier.UNCOMMON, LandingSound.HEAVY, TheBogwarden.Enums.OCEAN_BOGWARDEN_COLOR);
        counter = 0;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TRAPS_NEEDED + DESCRIPTIONS[1];
    }

    public void onTriggerTrap(AbstractTrapCard c) {
        if (++counter % TRAPS_NEEDED == 0) {
            counter = 0;
            flash();
            atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            applyToSelf(new EnergizedBogPower(adp(), 1));
        }
    }
}