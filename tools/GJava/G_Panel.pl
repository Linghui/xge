#!/usr/bin/perl -w
# for generate XPanel children

use strict;
use warnings;

use GJava;
use JSON;
use Getopt::Std;

my $class_output_dir = 'classes/';

my %opts = ();

# options
# h: help
# f: for file or stdin input
# none: for command line string input
getopts('hf', \%opts);

if( $opts{h} ){
    &usage();
    exit 0;
}

my $input = undef;

if( $opts{f} ){
    my @all_lines = <>;
    chomp @all_lines;
    $input = join '', @all_lines;
} else {
    
    if(!defined($ARGV[0])){
        print STDERR "Need json config string input\n";
        &usage();
        exit 1;
    }
    $input = $ARGV[0];
}

my $panel_config = from_json($input);

my $gJava = GJava->new();
my $panel_class = $gJava->generate_panel($panel_config);

open WRT, "> $class_output_dir".$panel_config->{'panel_name'}.".java"
or die "open file error $!";

print WRT "$panel_class";

close WRT;


sub usage(){
    
    print <<EOF;
Usage:
    perl $0 [-h | -f \$file_name/STDIN | \$json_string]
    -h: help
    -f: file or STDIN input mode
example: perl $0 -f config_demo.json
    no options: for command json string input
        
EOF
}

sub HELP_MESSAGE(){
    &usage();
    exit 0;
}
