import javax.swing.*;
import java.awt.*;

/**
 * View that displays the available settings in the game.
 * <p>
 * This class includes a header with an escape button and a title.
 * It also includes a center panel with toggle buttons and sliders
 * for adjusting the music and sound effect settings.
 * These controls are currently acting as placeholders that will be linked to the game logic in future development.
 */
public class SettingsView extends BaseView{
    private JPanel headerPanel;
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

    /**
     * Constructs the GameOverView and sets up all UI components.
     * <p>
     * This includes initializing button, labels, sliders and calling methods to create each panel.
     */
    public SettingsView(){
        super("Settings");

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

        createSettingsHeader();
        createSettingsCenterPanel();
    }

    /**
     * Creates a header panel containing the view title and an escape button.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left
     * - The title label is centered
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createSettingsHeader(){

        headerPanel = new JPanel(new BorderLayout(-90, 0));

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("SETTINGS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel containing setting controls for music and SFX.
     * <p>
     * The layout uses gridbaglayout to properly align components such as labels, toggle buttons and sliders.
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
