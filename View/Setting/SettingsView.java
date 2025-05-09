package View.Setting;

import View.ComponentsUtilities.Sound.SoundManager;
import View.ComponentsUtilities.BaseView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays the available settings in the game.
 * <p>
 * This class includes a header with an escape button and a title.
 * It also includes a center panel with toggle buttons and sliders
 * for adjusting the music and sound effect settings.
 * These controls are currently acting as placeholders that will be linked to the game logic in future development.
 */
public class SettingsView extends BaseView {
    private JPanel headerPanel;

    private EmptyBorder headerBorder;
    private JLabel titleLabel;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;

    private GridBagConstraints settingComponentsLayout;

    private JLabel musicLabel;
    private JToggleButton musicToggle;
    private JLabel musicLowLabel;
    private JSlider musicSlider;
    private JLabel musicHighLabel;

    private JLabel sfxLabel;
    private JToggleButton sfxToggle;
    private JLabel sfxLowLabel;
    private JSlider sfxSlider;
    private JLabel sfxHighLabel;

    private SoundManager soundManager;
    private String musicFilePath;

    /**
     * Constructs the GameOverView and sets up all UI components.
     * <p>
     * This includes initializing button, labels, sliders and calling methods to create each panel.
     */
    public SettingsView(){
        super("Settings", "/Resources/Images/Backgrounds/Background1.png");

        soundManager = new SoundManager();

        escapeButton = new JButton("ESCAPE");

        musicLabel = new JLabel("MUSIC", SwingConstants.RIGHT);
        musicToggle = new JToggleButton();
        musicLowLabel = new JLabel("LOW");
        musicSlider = new JSlider();
        musicHighLabel = new JLabel("HIGH");

        sfxLabel = new JLabel("SFX", SwingConstants.RIGHT);
        sfxToggle = new JToggleButton();
        sfxLowLabel = new JLabel("LOW");
        sfxSlider = new JSlider();
        sfxHighLabel = new JLabel("HIGH");

        addSoundFunctionality();

        createSettingsHeader();
        createSettingsCenterPanel();
    }

  // Getters
  /**
   * Returns the escape button.
   * <p>
   * @return the JButton for the "Escape" action
   */
  public JButton getEscapeButton() {
      return escapeButton;
  }

  /**
   * Returns the music label.
   * <p>
   * @return the JLabel for the music label
   */
  public JToggleButton getSfxToggle() {
      return sfxToggle;
  }

  /**
   * Returns the music toggle button.
   * <p>
   * @return the JToggleButton for the music toggle
   */
  public JToggleButton getMusicToggle() {
      return musicToggle;
  }

  /**
   * Returns the music slider.
   * <p>
   * @return the JSlider for the music volume
   */
  public JSlider getSfxSlider() {
      return sfxSlider;
  }

  /**
   * Returns the music slider.
   * <p>
   * @return the JSlider for the music volume
   */
  public JSlider getMusicSlider() {
      return musicSlider;
  }

  // Setters
  /**
   * Sets the escape button.
   * <p>
   * @param escapeButton the JButton to set for the "Escape" action
   */
  public void setEscapeButton(JButton escapeButton) {
      this.escapeButton = escapeButton;
  }

  /**
   * Sets the music label.
   * <p>
   * @param sfxToggle the JLabel to set for the music label
   */
  public void setSfxToggle(JToggleButton sfxToggle) {
      this.sfxToggle = sfxToggle;
  }

  /**
   * Sets the music toggle button.
   * <p>
   * @param musicToggle the JToggleButton to set for the music toggle
   */
  public void setMusicToggle(JToggleButton musicToggle) {
      this.musicToggle = musicToggle;
  }

  /**
   * Sets the music slider.
   * <p>
   * @param sfxSlider the JSlider to set for the music volume
   */
  public void setSfxSlider(JSlider sfxSlider) {
      this.sfxSlider = sfxSlider;
  }

  /**
   * Sets the music slider.
   * <p>
   * @param musicSlider the JSlider to set for the music volume
   */
  public void setMusicSlider(JSlider musicSlider) {
      this.musicSlider = musicSlider;
  }

    /**
     * Adds functionality to the music and SFX controls.
     * <p>
     * This method initializes the sliders and toggle buttons for music and SFX,
     * setting their default values and linking them to the SoundManager for 
     * playback and volume control.
     * <p>
     * The music slider adjusts the volume of the background music.
     * The music toggle button starts or stops the background music.
     * The SFX slider and toggle button are placeholders for future implementation.
     * <p>
     * This method ensures that the UI components interact with the 
     * sound system as expected.
     */
    private void addSoundFunctionality(){
        musicSlider.setMinimum(0);
        musicSlider.setMaximum(100);
        musicSlider.setValue(50);

        sfxSlider.setMinimum(0);
        sfxSlider.setMaximum(100);
        sfxSlider.setValue(50);

        musicFilePath = "Resources/Sound/513667__mrthenoronha__cartoon-game-theme-loop-4.wav";
        musicToggle.addActionListener(e -> {
            if (!musicToggle.isSelected()) {
                soundManager.playSound(musicFilePath);
            } else {
                soundManager.stopSound(musicFilePath);
            }
        });

        musicSlider.addChangeListener(e -> {
            int volume = musicSlider.getValue();
            soundManager.setVolume(musicFilePath, volume);
        });

        // Don't know how to implement this, but this is a placeholder for now
        sfxToggle.addActionListener(e -> {
            if (sfxToggle.isSelected()) {
                soundManager.playSound(null);
            } else {
                soundManager.playSound(null);
            }
        });

        sfxSlider.addChangeListener(e -> {
            int volume = sfxSlider.getValue();
            soundManager.setVolume(null, volume);
        });
    }

    /**
     * Creates a header panel containing the view title and an escape button.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left
     * - The title label is centered
     * <p>
     * Padding is applied using EmptyBorder to create space around the header content
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createSettingsHeader(){

        headerPanel = new JPanel(new BorderLayout(-90, 0));
        headerBorder = new EmptyBorder(20,20,10,10);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("SETTINGS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerBorder);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel containing setting controls for music and SFX.
     * <p>
     * The layout uses GridBagLayout to properly align components such as labels, toggle buttons and sliders.
     * The components are arranged in two rows: One for music and one for SFX.
     * The music and SFX controls are placeholders for future logic.
     * <p>
     * This panel is added to the center container of the frame.
     */
    private void createSettingsCenterPanel(){
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBackground(Color.GRAY);
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        innerCenterPanel = new JPanel(new GridBagLayout());
        settingComponentsLayout = new GridBagConstraints();
        settingComponentsLayout.insets = new Insets(5, 30, 5, 30);
        settingComponentsLayout.fill = GridBagConstraints.HORIZONTAL;

        // First row - Music
        settingComponentsLayout.gridy = 0;
        settingComponentsLayout.gridx = 0;
        innerCenterPanel.add(musicLabel, settingComponentsLayout);

        settingComponentsLayout.gridx = 1;
        innerCenterPanel.add(musicToggle, settingComponentsLayout);

        settingComponentsLayout.gridx = 2;
        innerCenterPanel.add(musicLowLabel, settingComponentsLayout);

        settingComponentsLayout.gridx = 3;
        innerCenterPanel.add(musicSlider, settingComponentsLayout);
        settingComponentsLayout.weightx = 0;

        settingComponentsLayout.gridx = 4;
        innerCenterPanel.add(musicHighLabel, settingComponentsLayout);

        // Second row - SFX
        settingComponentsLayout.gridy = 1;
        settingComponentsLayout.gridx = 0;
        innerCenterPanel.add(sfxLabel, settingComponentsLayout);

        settingComponentsLayout.gridx = 1;
        innerCenterPanel.add(sfxToggle, settingComponentsLayout);

        settingComponentsLayout.gridx = 2;
        innerCenterPanel.add(sfxLowLabel, settingComponentsLayout);

        settingComponentsLayout.gridx = 3;
        innerCenterPanel.add(sfxSlider, settingComponentsLayout);
        settingComponentsLayout.weightx = 0;

        settingComponentsLayout.gridx = 4;
        innerCenterPanel.add(sfxHighLabel, settingComponentsLayout);

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.setOpaque(false);

        frame.add(outerCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the SettingsView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */

    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SettingsView settingsView = new SettingsView();
            settingsView.show();
        });

    } */
}
