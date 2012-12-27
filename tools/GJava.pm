#!/usr/bin/perl -w
# use for generate Java code
package GJava;

use strict;
use warnings;
use Data::Dumper qw/Dumper/;
use Template;

my $PACK_SUFFIX = '/pack';
my $TEMPLATE_DIR = 'templates';
my $TEMPLATE_DEFAULT_VERSION = '0.9.7';
my $TEMPLATE_PATH = undef;
my %SUPPORT_VERSION = (
            '96' => '0.9.6',
            '97' => '0.9.7',
            );

# class
my $PACKAGE         = 'package';
my $IMPORT          = 'import';

my $CLASS_NAME      = 'class_name';
my $SCENE_NAME_KEY  = 'scene_name';
my $EXTENDS_KEY     = 'extends';
my $PARENT          = 'parent';
my $IMPLEMENTS_KEY  = 'implements';

# screen
my $SCREEN_WIDHT    = 'screen_width';
my $SCREEN_HEIGHT   = 'screen_height';

my $BACKGROUND_KEY  = 'background';
my $MOVEMENT_LIMIT_Y    = 'limit_y';

my $LEADING_ROLE_SRC    =   'leading_role_src';
my $LEADING_ROLE_JSON   =   'leading_role_json';
my $LEADING_ROLE_PACK   =   'leading_role_pack';

# resource
my $SKIN_KEY    =   'skin';
my $SOURCE_LOADING  = 'source_loading';
my $SOURCE_UNLOADING  = 'source_unloading';
my $INIT_KEY    =   'init';
my $INIT_BUTTONS    =   'init_buttons';
my $LOADING_BACKGROUND_KEY  =   'loading_backgroud';

my $LADING_BAR_KEY  =   'loading_bar';
my $BAR_TOP         =   'bar_top';
my $BAR_BOTTOM      =   'bar_bottom';
my $BAR_WIDTH       =   'bar_width';
my $BAR_HEIGHT      =   'bar_height';
my $BAR_X           =   'bar_x';
my $BAR_Y           =   'bar_y';
my $BAR_SOURCE_COUNT   =   'bar_source_count';

# button
my $NAME    =   'name';
my $TEXT_BUTTONS_KEY    =   'text_buttons';
my $TEXT_KEY    =   'text';
my $X_KEY   =   'x';
my $Y_KEY   =   'y';
my $LAYOUT_KEY  = 'layout';
my $HAS_PARAMETER = 'p';
my $HAS_NO_PARAMETER = 'np';
my $ADD_TO_KEY = 'add_to';

# text field
my $INIT_TEXTFIELDS = 'init_textfields';
my $TEXT_FIELDS_KEY = 'text_fields';
my $HINT_KEY        = 'hint';

# label
my $INIT_LABELS =   'init_labels';
my $LABELS_KEY  =   'labels';
my $STYLE_KEY   =   'style';

# image button
my $IMAGE_BUTTONS_KEY   =   'image_buttons';
my $IMAGE_BUTTONS_DOWN_REGION_KEY   =   'down_region';
my $IMAGE_BUTTONS_UP_REGION_KEY     =   'up_region';


# npc
my $INIT_NPCS   =   'init_npcs';
my $NPCS_KEY =   'npcs';
my $NPC_NAME    =   'npc_name';
my $NPC_SRC     =   'npc_src';

# action function
my $ACTIONS = 'actions';
my $SCOPE   = 'scope';
my $RETURN_TYPE = 'return_type';
my $METHOD_NAME = 'method_name';
my $PARAMETERS  = 'parameters';
my $RETURN_VALUE= 'return_value';

my @all_button_actions = ();    # use to collect action names buttons need
my %all_actor_layout = ();

# add tos
my $ADD_TO_BOTTOM = 'this.addActorBottom';
my $ADD_TO_BACKGROUND = 'this.addActorBackground';
my $ADD_ACTOR   = 'this.addActor';


# panel
my $PANEL_NAME  = 'panel_name';
my $WIDTH       = 'width';
my $HEIGHT      = 'height';

# layout
my $INIT_LAYOUT = 'init_layout';

# xcell
my $ROW_KEY     = 'row';
my $XCELL_KEY   = 'xcells';


sub new(){
    my $class = shift;
    my $version = shift;
    
    my $self = {};
    
    if( defined($version) && exists $SUPPORT_VERSION{$version}){
        $TEMPLATE_PATH = "$TEMPLATE_DIR/".$SUPPORT_VERSION{$version}."/";
    } else {
        $TEMPLATE_PATH = "$TEMPLATE_DIR/$TEMPLATE_DEFAULT_VERSION/";
    }
    
    bless $self, $class;
    return $self;
}

sub generate_scene(){
    my $self = shift;
    my $config = shift;

    &reset_actions();
    
#    print Dumper($config);
    
    # some useful options (see below for full list)
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/class.t";
    
    my $vars = {
        $CLASS_NAME  => $config->{$SCENE_NAME_KEY},
        $SCREEN_WIDHT  => $config->{$SCREEN_WIDHT},
        $SCREEN_HEIGHT  => $config->{$SCREEN_HEIGHT},
#        $BACKGROUND_KEY  => $config->{$BACKGROUND_KEY},
        $MOVEMENT_LIMIT_Y   =>  $config->{$MOVEMENT_LIMIT_Y},
        $LOADING_BACKGROUND_KEY  => $config->{$LOADING_BACKGROUND_KEY},
        $BAR_TOP  => $config->{$LADING_BAR_KEY}->{$BAR_TOP},
        $BAR_BOTTOM  => $config->{$LADING_BAR_KEY}->{$BAR_BOTTOM},
        $BAR_WIDTH  => $config->{$LADING_BAR_KEY}->{$BAR_WIDTH},
        $BAR_HEIGHT  => $config->{$LADING_BAR_KEY}->{$BAR_HEIGHT},
        $BAR_X  => $config->{$LADING_BAR_KEY}->{$BAR_X},
        $BAR_Y  => $config->{$LADING_BAR_KEY}->{$BAR_Y},
        $SKIN_KEY  => $config->{$SKIN_KEY},
        $PACKAGE    => 'package com.gol.game;',
    };

    if(defined($config->{$IMPLEMENTS_KEY})){
        $vars->{$IMPLEMENTS_KEY} = "$IMPLEMENTS_KEY ".$config->{$IMPLEMENTS_KEY};
    }
    
    if(defined($config->{$EXTENDS_KEY})){
        $vars->{$EXTENDS_KEY} = "$EXTENDS_KEY ".$config->{$EXTENDS_KEY};
    }
    
    # background
    $vars->{$BACKGROUND_KEY} = $self->get_background_resion($config->{$BACKGROUND_KEY});
    
    
    # loading and unloading source
#    my $source_loading_list = $config->{$SOURCE_LOADING};
    my $source_loading_list = $self->get_loading_list($config);
    my $all_src_load = "";
    my $all_src_unload = "";
    my $all_source_count = 0;
    for my $one (@$source_loading_list){
        next if !defined($one);
        
        my $load_line = $self->get_load_line($one);
        $all_src_load .= $load_line;
        
        my $unload_line = $self->get_unload_line($one);
        $all_src_unload .= $unload_line;
        
        $all_source_count++;
    }
    
    $vars->{$SOURCE_LOADING} = $all_src_load;
    $vars->{$SOURCE_UNLOADING} = $all_src_unload;
    $vars->{$BAR_SOURCE_COUNT}  = $all_source_count;
    
    my $init_labels_value  = $self->get_init_labels_value($config->{$LABELS_KEY}, $ADD_TO_BOTTOM);
    $vars->{$INIT_LABELS} = $init_labels_value;
    
    my $init_buttons_value = $self->get_init_buttons_value($config->{$TEXT_BUTTONS_KEY}
                                                            , $config->{$IMAGE_BUTTONS_KEY}
                                                            , $ADD_TO_BOTTOM);
    $vars->{$INIT_BUTTONS} = $init_buttons_value;
    
    my $init_textfields_value = $self->get_init_textfileds_value($config->{$TEXT_FIELDS_KEY}, $ADD_TO_BOTTOM);
    $vars->{$INIT_TEXTFIELDS} = $init_textfields_value;

    if(defined($config->{$LEADING_ROLE_SRC})){
        
        # deal leading role resource
        my ($leading_role_src_id)  =   $config->{$LEADING_ROLE_SRC} =~ /(\d+)$/;
        
        $vars->{$LEADING_ROLE_JSON}   = $config->{$LEADING_ROLE_SRC} .'/'.$leading_role_src_id.'.json',
        $vars->{$LEADING_ROLE_PACK}   = $config->{$LEADING_ROLE_SRC} .$PACK_SUFFIX,
        $vars->{$PARENT}   = 'RPGScreen';
        
    } else {
        $vars->{$PARENT}   = 'TacticsScreen';
    }
    
    my $init_npcs_value =   $self->get_init_npcs_value($config, $ADD_TO_BACKGROUND);
    $vars->{$INIT_NPCS} = $init_npcs_value;
    
    my $actions = $self->get_actions();
    
    $vars->{$ACTIONS} = $actions;
    
    my $output = undef;
    
    # process input template, substituting variables
    $template->process($input, $vars, \$output)
    || die $template->error();
    
    return $output;
    
}

sub get_background_resion(){
    
    my $self = shift;
    my $source = shift;
    
    if( !defined($source)){
        return "null";
    }
    
    my $line = "";
    
    if($source =~ /\.png/){
        $line = " new TextureRegion(manager.get(\"$source\", Texture.class));\n";
    } elsif ($source =~ /pack/){
        my ($pack_file, $region_name) = split /\?/, $source;
        $line = " manager.get(\"$pack_file\", TextureAtlas.class).findRegion(\"$region_name\");\n";
    }
    
    return $line;
}

sub get_load_line(){
    my $self = shift;
    my $source = shift;
    
    my $source_class;

    
    if($source =~ /\.png/){
        $source_class   = "Texture.class";
    } elsif ($source =~ /\.json/){
        $source_class   = "Skin.class";
    } elsif ($source =~ /\.fnt/){
        $source_class   = "BitmapFont.class";
    } elsif ($source =~ /pack/){
        $source_class   = "TextureAtlas.class";        
    }
    my $line = "        manager.load(\"$source\", $source_class);\n";    
    
    return $line;
}

sub get_unload_line(){
    my $self = shift;
    my $source = shift;
    
    my $line = "        manager.unload(\"$source\");\n";
    
    return $line;
}

# pick up all the resources needed and put them into load list
sub get_loading_list(){
    my $self = shift;
    my $config = shift;

    my @list = ();

    push @list, $config->{$SKIN_KEY};
    
    my $background_resource = $config->{$BACKGROUND_KEY};
    if( defined($background_resource) && $background_resource =~ /pack/ ){
        ($background_resource) = split /\?/, $background_resource;
    }
    push @list, $background_resource;
    
    if(defined($config->{$LEADING_ROLE_SRC})){
        push @list, $config->{$LEADING_ROLE_SRC}.$PACK_SUFFIX;
    }

    
    
    my $npcs = $config->{$NPCS_KEY};
    for my $one_npc (@$npcs){
        push @list, $one_npc->{$NPC_SRC}.$PACK_SUFFIX;
    }
    
    
    return \@list;
}

sub get_init_labels_value(){
    
    my $self = shift;
    
    my $labels = shift; # labels array ref
    
    my $add_to  = shift;
    
    my $all_label_lines = "";
    
    my $LABEL_TEXT  =   'label_text';
    my $LABEL_STYLE =   'label_style';
    my $LABEL_X     =   'label_x';
    my $LABEL_Y     =   'label_y';
    
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/label.t";

    for my $one_label (@$labels){
        
        # record its layout and use them later
        if( $one_label->{$LAYOUT_KEY} ){
            $all_actor_layout{$one_label->{$NAME}} = $one_label->{$LAYOUT_KEY};
        }
        
        my $vars = {
            $NAME           =>   $one_label->{$NAME},
            $LABEL_TEXT     =>   $one_label->{$TEXT_KEY},
            $LABEL_STYLE    =>   $one_label->{$STYLE_KEY},
            $LABEL_X    =>   $one_label->{$X_KEY},
            $LABEL_Y    =>   $one_label->{$Y_KEY},
            $ADD_TO_KEY =>   $add_to,
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_label_lines .= $output;
        
    }
    
    return $all_label_lines;
}

sub get_init_buttons_value(){
    my $self    = shift;
    
    my $text_buttons  = shift;  # text button array ref
    my $image_buttons  = shift; # image button array ref
    
    my $add_to  = shift;
    
    my $all_button_lines = "";

    my $BUTTON_TEXT = 'button_text';
    my $BUTTON_X = 'button_x';
    my $BUTTON_Y = 'button_y';
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/text_button.t";
    
    for my $one_button (@$text_buttons){
        
        # record its layout and use them later
        if( $one_button->{$LAYOUT_KEY} ){
            $all_actor_layout{$one_button->{$NAME}} = $one_button->{$LAYOUT_KEY};
        }
        
        
#        print Dumper($one_button);
        my $vars = {
            $NAME       =>   $one_button->{$NAME},
            $BUTTON_TEXT    =>   $one_button->{$TEXT_KEY},
            $BUTTON_X    =>   $one_button->{$X_KEY},
            $BUTTON_Y    =>   $one_button->{$Y_KEY},
            $ADD_TO_KEY  =>   $add_to,
        };
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_button_lines .= $output;
        
        push @all_button_actions,  $one_button->{$NAME};
    }
    
    $input = "$TEMPLATE_PATH/image_button.t";
    
    for my $one_button (@$image_buttons){
        
        
        # record its layout and use them later
        if( $one_button->{$LAYOUT_KEY} ){
            $all_actor_layout{$one_button->{$NAME}} = $one_button->{$LAYOUT_KEY};
        }
        
        my $vars = {
            $NAME       =>   $one_button->{$NAME},
            $IMAGE_BUTTONS_DOWN_REGION_KEY  => 'null',
            $IMAGE_BUTTONS_UP_REGION_KEY  => 'null',
            $BUTTON_X    =>   $one_button->{$X_KEY},
            $BUTTON_Y    =>   $one_button->{$Y_KEY},
            $ADD_TO_KEY  =>   $add_to,
        };
        
        if(defined($one_button->{$IMAGE_BUTTONS_DOWN_REGION_KEY})){
            $vars->{$IMAGE_BUTTONS_DOWN_REGION_KEY} = 'skin.getRegion("'.$one_button->{$IMAGE_BUTTONS_DOWN_REGION_KEY}.'")';
        }
        
        if(defined($one_button->{$IMAGE_BUTTONS_UP_REGION_KEY})){
            $vars->{$IMAGE_BUTTONS_UP_REGION_KEY} = 'skin.getRegion("'.$one_button->{$IMAGE_BUTTONS_UP_REGION_KEY}.'")';
        }
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_button_lines .= $output;
        
        push @all_button_actions,  $one_button->{$NAME};
    }
    
    
    return $all_button_lines;
}

sub get_init_textfileds_value(){
    my $self        =   shift;
    my $textfields  =   shift;
    my $add_to      =   shift;

    my $all_textfiled_lines = "";
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/textfield.t";
    
    for my $one (@$textfields){
        my $vars = {
            $NAME   =>    $one->{$NAME},
            $X_KEY   =>   $one->{$X_KEY},
            $Y_KEY   =>   $one->{$Y_KEY},
            $HINT_KEY =>  $one->{$HINT_KEY},
            $ADD_TO_KEY =>  $add_to,
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_textfiled_lines .= $output;
        
    }
    return $all_textfiled_lines;
}

sub get_init_npcs_value(){
    my $self    =   shift;
    my $config  =   shift;
    my $add_to  = shift;
    
    my $all_npc_lines = "";
    
    
    my $NPC_JSON = 'npc_json';
    my $NPC_PACK = 'npc_pack';
    my $NPC_X = 'npc_x';
    my $NPC_Y = 'npc_y';
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/npc.t";
    
    my $npcs = $config->{$NPCS_KEY};
    for my $one_npc (@$npcs){
        
        my ($src_id)  =   $one_npc->{$NPC_SRC} =~ /(\d+)$/;
#        print "src_id ====== $src_id\n";
        my $vars = {
            $NAME   =>   $one_npc->{$NAME},
            $NPC_JSON   => $one_npc->{$NPC_SRC}.'/'.$src_id.'.json',
            $NPC_PACK   => $one_npc->{$NPC_SRC}.$PACK_SUFFIX,
            $NPC_X   =>   $one_npc->{$X_KEY},
            $NPC_Y   =>   $one_npc->{$Y_KEY},
            $ADD_TO_KEY =>  $add_to,
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_npc_lines .= $output;
        
        push @all_button_actions,  $one_npc->{$NAME};
    }
    
    return $all_npc_lines;
}


# not finished
sub get_lineactors(){
    
    my $all_lineactors_lines = "";
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/lineactors.t";
    
    
}

sub get_actions(){
    
    my $all_action_lines = "";
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/method.t";
    
    my $ACTIONS = 'actions';
    my $SCOPE   = 'scope';
    my $RETURN_TYPE = 'return_type';
    my $METHOD_NAME = 'method_name';
    my $PARAMETERS  = 'parameters';
    my $RETURN_VALUE= 'return_value';
    
    for my $one(@all_button_actions){
        my $vars = {
            $METHOD_NAME    =>   $one.'Action',
            $SCOPE          =>  'public',
            $RETURN_TYPE    =>  'void',
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_action_lines .= $output;
    }
    
    return $all_action_lines;
}

sub reset_actions(){
    @all_button_actions = ();
}

my $WINDOW_NAME = 'window_name';
my $TITLE       = 'title';
my $PANELS_KEY  = 'panels';
my $INIT_PANELS = 'init_panels';
my $MEANINGLESS_ADD = 'meaningLess';

sub generate_window(){
    my $self = shift;
    my $window_config = shift;
    
    &reset_actions();
    
    my @layouts = ();
    
    my $vars = {
        $WINDOW_NAME => $window_config->{$WINDOW_NAME},
        $TITLE => $window_config->{$TITLE},
    };
    
    # all the buttons
    my $text_buttons_config     = [];
    my $image_buttons_config    = [];
    
    if( defined($window_config->{$TEXT_BUTTONS_KEY}) ){
        $text_buttons_config = $window_config->{$TEXT_BUTTONS_KEY};
        push @layouts, @$text_buttons_config;
    }
    
    if( defined($window_config->{$IMAGE_BUTTONS_KEY}) ){
        $image_buttons_config = $window_config->{$IMAGE_BUTTONS_KEY};
        push @layouts, @$image_buttons_config;
    }
    
    my $init_buttons_value = $self->get_init_buttons_value($text_buttons_config
                                                            , $image_buttons_config
                                                            , $MEANINGLESS_ADD);
    
    $vars->{$INIT_BUTTONS} = $init_buttons_value;
    # button ends
    
    
    # all the labels
    my $label_config = [];
    
    if( defined($window_config->{$LABELS_KEY}) ){
        $label_config = $window_config->{$LABELS_KEY};
        push @layouts, @$label_config;
    }
    
    my $init_labels_value  = $self->get_init_labels_value($label_config, $MEANINGLESS_ADD);
    $vars->{$INIT_LABELS} = $init_labels_value;
    # label ends
    
    
    my $textfield_config = [];
    
    if( defined($window_config->{$TEXT_FIELDS_KEY}) ){
        $textfield_config = $window_config->{$TEXT_FIELDS_KEY};
        push @layouts, @$textfield_config;
    }
    
    my $init_textfields_value = $self->get_init_textfileds_value($textfield_config, $MEANINGLESS_ADD);
    $vars->{$INIT_TEXTFIELDS} = $init_textfields_value;
    
    
    # all the panels
    my $panel_config = [];
    if( defined($window_config->{$PANELS_KEY}) ){
        $panel_config = $window_config->{$PANELS_KEY};
        push @layouts, @$panel_config;
    }
    
    my $init_panels_value  = $self->get_init_panels_value($panel_config);
    $vars->{$INIT_PANELS} = $init_panels_value;
    # panel ends
    
    
    # all the layout, need optimise in the future
    my $layout = "";
    for my $element (@layouts){
        $layout .= "        this.add(".$element->{$NAME}.")";
        if( defined $element->{$WIDTH}){
            $layout .= ".width(".$element->{$WIDTH}.")";
        }
        if( defined $element->{$HEIGHT}){
            $layout .= ".height(".$element->{$HEIGHT}.")";
        }
        $layout .= ";\n";
        $layout .= "        this.row();\n";
    }
    
    $vars->{$INIT_LAYOUT} = $layout;
    # layout ends
    
    
    # all the actions
    my $actions = $self->get_actions();
    
    $vars->{$ACTIONS} = $actions;
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/window.t";
    
    
    my $output = undef;
    
    # process input template, substituting variables
    $template->process($input, $vars, \$output)
    || die $template->error();
    
    return $output;
    
}

sub get_init_panels_value(){
    my $self = shift;
    my $panel_config = shift;

    my $all_lines = "";
    for my $one (@$panel_config){

        $all_lines .= "        $one->{$CLASS_NAME} $one->{$NAME} = new $one->{$CLASS_NAME}(manager, skin);\n";
    }
    
    return $all_lines;
}

my $INIT_XCELLS = 'init_xcells';
my $ATLAS       = 'atlas';
my $SRC_KEY     = 'src';
my $CHOOSE_FRAME= 'choose_frame';
my $ICON        = 'icon';
sub get_init_xcells_value(){
    my $self = shift;
    my $xcell_config = shift;
    
    my $all_lines = "";
    
    my $resources = shift @$xcell_config;
    
    for my $one (@$resources){
        $ all_lines .= "        TextureAtlas $one->{$NAME} = manager.get(\"$one->{$SRC_KEY}\", TextureAtlas.class);\n";
    }
    
    $ all_lines .= "\n";
    
    for my $one (@$xcell_config){
        $ all_lines .= "        XCell $one->{$NAME} = new XCell(skin.getPatch(\"$one->{$BACKGROUND_KEY}\"), $one->{$WIDTH}, $one->{$HEIGHT}, $one->{$ATLAS});\n";
        if( defined($one->{$CHOOSE_FRAME}) ){
            $ all_lines .= "        $one->{$NAME}.setChooseFrame(skin.getPatch(\"$one->{$CHOOSE_FRAME}\"));\n";
        }
        if( defined($one->{$ICON}) ){
            $ all_lines .= "        $one->{$NAME}.setIcon(\"$one->{$ICON}\");\n";
        }
    }
    
    return $all_lines;
}

sub generate_panel(){
    
    my $self = shift;
    my $panel_config = shift;

    &reset_actions();
    
    my $vars = {
        $PANEL_NAME => $panel_config->{$PANEL_NAME},
        $WIDTH      => $panel_config->{$WIDTH},
        $HEIGHT     => $panel_config->{$HEIGHT},
    };
    
    my $text_buttons_config     = [];
    my $image_buttons_config    = [];
    
    if( defined($panel_config->{$TEXT_BUTTONS_KEY}) ){
        $text_buttons_config = $panel_config->{$TEXT_BUTTONS_KEY};
    }
    
    if( defined($panel_config->{$IMAGE_BUTTONS_KEY}) ){
        $image_buttons_config = $panel_config->{$IMAGE_BUTTONS_KEY};
    }
    
    my $init_buttons_value = $self->get_init_buttons_value($text_buttons_config
                                                        , $image_buttons_config
                                                        , $ADD_ACTOR);

    $vars->{$INIT_BUTTONS} = $init_buttons_value;

    my $label_config = [];
    
    if( defined($panel_config->{$LABELS_KEY}) ){
        $label_config = $panel_config->{$LABELS_KEY};
    }
    
    my $init_labels_value  = $self->get_init_labels_value($label_config, $ADD_ACTOR);
    $vars->{$INIT_LABELS} = $init_labels_value;

    my $actions = $self->get_actions();
    
    $vars->{$ACTIONS} = $actions;
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/panel.t";
    
    
    my $output = undef;
    
    # process input template, substituting variables
    $template->process($input, $vars, \$output)
    || die $template->error();
    
    return $output;
    
}



sub generate_panel_table(){
    
    my $self = shift;
    my $panel_config = shift;
    
#    my @layouts = ();
    
    &reset_actions();
    
    my $vars = {
        $NAME => $panel_config->{$NAME},
    };
    
    # button
    my $text_buttons_config     = [];
    my $image_buttons_config    = [];
    
    if( defined($panel_config->{$TEXT_BUTTONS_KEY}) ){
        $text_buttons_config = $panel_config->{$TEXT_BUTTONS_KEY};
#        push @layouts, @$text_buttons_config;
    }
    
    if( defined($panel_config->{$IMAGE_BUTTONS_KEY}) ){
        $image_buttons_config = $panel_config->{$IMAGE_BUTTONS_KEY};
#        push @layouts, @$image_buttons_config;
    }
    
    my $init_buttons_value = $self->get_init_buttons_value($text_buttons_config
                                                            , $image_buttons_config
                                                            , $MEANINGLESS_ADD);
    $vars->{$INIT_BUTTONS} = $init_buttons_value;
    
    # label
    my $label_config = [];
    
    if( defined($panel_config->{$LABELS_KEY}) ){
        $label_config = $panel_config->{$LABELS_KEY};
#        push @layouts, @$label_config;
    }
    
    my $init_labels_value  = $self->get_init_labels_value($label_config, $MEANINGLESS_ADD);
    $vars->{$INIT_LABELS} = $init_labels_value;
    
    # xcell
    my $xcell_config = [];
    if( defined($panel_config->{$XCELL_KEY}) ){
        $xcell_config = $panel_config->{$XCELL_KEY};
        
        # the first one is resource , so kick it out
        my @temp = @$xcell_config;
#        push @layouts, @temp[ 1 .. $#temp ];
    }
    
    my $init_xcells_value  = $self->get_init_xcells_value($xcell_config, $MEANINGLESS_ADD);
    $vars->{$INIT_XCELLS} = $init_xcells_value;
    
    
    my @layouts = @{$panel_config->{$ROW_KEY}};
    
    # layout
    my $layout = "";
    for my $element (@layouts){
        if( $element eq $ROW_KEY ){
            $layout .= "        this.row();\n";
        } else {
            $layout .= "        this.add($element)";
            if( defined( $all_actor_layout{$element} ) ){
                
                my $ps = $all_actor_layout{$element}->{$HAS_PARAMETER};
                for my $p_name ( keys %$ps ){
                    $layout .= ".$p_name(". $ps->{$p_name}.")";
                }
                
                my $nps = $all_actor_layout{$element}->{$HAS_NO_PARAMETER};
                for my $p_name ( @$nps){
                    $layout .= ".$p_name()";
                }
            }
            $layout .= ";\n";
        }

        
    }
    $vars->{$INIT_LAYOUT} = $layout;
    
    
    my $actions = $self->get_actions();
    
    
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/panel_table.t";
    
    
    my $output = undef;
    
    # process input template, substituting variables
    $template->process($input, $vars, \$output)
    || die $template->error();
    

    $vars = undef;
    
    $vars = {
        $NAME => $panel_config->{$NAME} . "Actions",
        "table_class" => $panel_config->{$NAME},
        $ACTIONS => $actions,
    };
    
    $input = "$TEMPLATE_PATH/action_class.t";
    
    
    my $action_output = undef;
    
    # process input template, substituting variables
    $template->process($input, $vars, \$action_output)
    || die $template->error();

    
    return ($output, $action_output);
    
}

1



