package tk.itsnotalan.swkilleffect.effects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.itsnotalan.swkilleffect.SWKillEffect;
import tk.itsnotalan.swkilleffect.utils.ConfigUtil;
import tk.itsnotalan.swkilleffect.utils.ParticleEffect;

public class KillEffectNotePulse extends KillEffect {

	public KillEffectNotePulse() {
		super("Notes Pulse", "NOTES_PULSE", Material.NOTE_BLOCK);
	}

	@Override
	public void play(Player p) {
		Location loc = p.getLocation();

		float offsetX = ConfigUtil.HEART_EXPLOSION_OFFSET_X;
		float offsetY = ConfigUtil.HEART_EXPLOSION_OFFSET_Y;
		float offsetZ = ConfigUtil.HEART_EXPLOSION_OFFSET_Z;
		float addX = ConfigUtil.HEART_EXPLOSION_ADD_X;
		float addY = ConfigUtil.HEART_EXPLOSION_ADD_Y;
		float addZ = ConfigUtil.HEART_EXPLOSION_ADD_Z;
		float speed = ConfigUtil.HEART_EXPLOSION_SPEED;

		BukkitRunnable runnable = new BukkitRunnable() {
			int i = 5;
			float m = 0;

			@Override
			public void run() {
				if (i > 0) {

					//
					float size=1;
					m += 0.3;
					for(int d = 0; d <= 45; d += 1) {

						Location particleLoc = new Location(loc.getWorld(), loc.getX(), loc.getY() + m, loc.getZ());
						particleLoc.setX(loc.getX() + Math.cos(d) * size);
						particleLoc.setZ(loc.getZ() + Math.sin(d) * size);

						ParticleEffect.NOTE.display(0, 0, 0, 1, 1, particleLoc, 256f);
					}
					//

					i--;
					if (i == 0) {
						cancel();
					}
				}
			}

		};
		runnable.runTaskTimer(SWKillEffect.getInstance(), 1, 5);
	}

}
